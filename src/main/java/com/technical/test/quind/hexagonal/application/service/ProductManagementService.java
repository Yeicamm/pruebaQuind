package com.technical.test.quind.hexagonal.application.service;

import com.technical.test.quind.hexagonal.application.usecases.ProductService;
import com.technical.test.quind.hexagonal.domain.model.constant.MessageApplication;
import com.technical.test.quind.hexagonal.domain.model.dto.EditAccountStatusDto;
import com.technical.test.quind.hexagonal.domain.model.dto.request.RequestAccountClientDto;
import com.technical.test.quind.hexagonal.domain.model.enums.AccountState;
import com.technical.test.quind.hexagonal.domain.model.enums.AccountType;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductManagementService implements ProductService {
    private final ProductRepository productRepository;
    private final AccountManagementService accountManagementService;

    public Object accountCreate(RequestAccountClientDto requestAccountClientDto) {
        requestAccountClientDto.getProductDto().setAccountNumber(null);
        String accountNumber;

        if (requestAccountClientDto.getAccountType().equals(AccountType.SAVINGS.name())) {
            accountNumber = accountManagementService.generateNumberAccountRandom("53");
            requestAccountClientDto.getProductDto().setAccountNumber(accountNumber);
            return accountManagementService.createSavingsAccount(requestAccountClientDto);

        } else if (requestAccountClientDto.getAccountType().equals(AccountType.CURRENT.name())) {
            accountNumber = accountManagementService.generateNumberAccountRandom("33");
            requestAccountClientDto.getProductDto().setAccountNumber(accountNumber);
            return accountManagementService.createCurrentAccount(requestAccountClientDto);
        }
        return MessageApplication.CANNOTCREATEDIFFERENTACCOUNT;
    }

    @Override
    public Object accountStateUpdate(EditAccountStatusDto editAccountStatusDto) {
        Optional<ProductEntity> productEntity = productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber());
        if (productEntity.isPresent()) {
            productEntity.get().setAccountState(editAccountStatusDto.getAccountState());
            productEntity.get().setDateModified(LocalDateTime.now());
            productRepository.save(productEntity.get());
            return MessageApplication.UPDATEACCOUNTS;
        }
        return MessageApplication.ACCOUNTNOTFOUND;
    }

    @Override
    public Object accountCanceled(EditAccountStatusDto editAccountStatusDto) {
        var balanceCompare = 0;
        Optional<ProductEntity> productEntity = productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber());
        if (productEntity.isPresent()) {
            if (productEntity.get().getBalance().compareTo(BigDecimal.ZERO) == balanceCompare) {
                productEntity.get().setAccountState(AccountState.CANCELLED);
                productEntity.get().setDateModified(LocalDateTime.now());
                productRepository.save(productEntity.get());
                return MessageApplication.ACCOUNTCANCELLED;
            }
            return MessageApplication.ACCOUNTCANCELLED;
        }
        return MessageApplication.ACCOUNTNOTFOUND;
    }

    @Override
    public Object consignMoney(String accountNumber, BigDecimal balance) {
        var balanceCompare = 0;
        if (balance.compareTo(BigDecimal.ZERO) <= balanceCompare) {
            return MessageApplication.AMOUNTNOTNEGATIVE;
        }
        Optional<ProductEntity> productEntity = productRepository.findProductEntityByAccountNumber(accountNumber);
        if (productEntity.isEmpty()) {
            return MessageApplication.ACCOUNTNOTFOUND;
        }
        productEntity.get().setBalance(productEntity.get().getBalance().add(balance));
        productEntity.get().setDateModified(LocalDateTime.now());
        return productRepository.save(productEntity.get());
    }

    @Override
    public Object withdrawMoney(String accountNumber, BigDecimal balance) {

        Optional<ProductEntity> productEntity = productRepository.findProductEntityByAccountNumber(accountNumber);

        if (productEntity.isEmpty()) {
            return MessageApplication.ACCOUNTNOTFOUND;
        }
        if (productEntity.get().getBalance().compareTo(balance) >= 0) {
            productEntity.get().setBalance(productEntity.get().getBalance().subtract(balance));
            productEntity.get().setDateModified(LocalDateTime.now());
            return productRepository.save(productEntity.get());
        } else {
            return MessageApplication.INSUFFICIENTBALANCE;
        }
    }

    @Override
    public void transferMoney(String accountOrigin, String accountDestination, BigDecimal balance) {
        withdrawMoney(accountOrigin, balance);
        withdrawMoney(accountDestination, balance);
    }
}
