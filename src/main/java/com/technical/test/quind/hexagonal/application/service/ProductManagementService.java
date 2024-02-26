package com.technical.test.quind.hexagonal.application.service;

import com.technical.test.quind.hexagonal.application.usecases.ProductService;
import com.technical.test.quind.hexagonal.domain.model.constant.MessageApplication;
import com.technical.test.quind.hexagonal.domain.model.dto.EditAccountStatusDto;
import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;
import com.technical.test.quind.hexagonal.domain.model.enums.AccountState;
import com.technical.test.quind.hexagonal.domain.model.enums.AccountType;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.repository.ClientRepository;
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
    private final ClientRepository clientRepository;

    public Object accountCreate(ProductDto productDto) {
        productDto.setAccountNumber(null);
        String accountNumber;
        if (productDto.getBalance().compareTo(BigDecimal.ZERO) >= 0) {
            if (clientRepository.existsById(productDto.getClientId())) {
                if (productDto.getAccountType().equals(AccountType.SAVINGS.name())) {
                    accountNumber = accountManagementService.generateNumberAccountRandom("53");
                    productDto.setAccountNumber(accountNumber);
                    return accountManagementService.createSavingsAccount(productDto);
                } else if (productDto.getAccountType().equals(AccountType.CURRENT.name())) {
                    accountNumber = accountManagementService.generateNumberAccountRandom("33");
                    productDto.setAccountNumber(accountNumber);
                    return accountManagementService.createCurrentAccount(productDto);
                }
                return MessageApplication.ACCOUNT_MUST_TYPE_SAVINGS_CURRENT;
            }
            return MessageApplication.VALIDATION_CLIENT_PRODUCT;
        }
        return MessageApplication.VALUE_ACCOUNT_GREATER_TO_ZERO;
    }

    @Override
    public Object accountStateUpdate(EditAccountStatusDto editAccountStatusDto) {
        Optional<ProductEntity> productEntity = productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber());
        if (productEntity.isPresent()) {
            if (productEntity.get().getBalance().compareTo(BigDecimal.ZERO) == 0 && editAccountStatusDto.getAccountState() == AccountState.CANCELLED) {
                productEntity.get().setAccountState(editAccountStatusDto.getAccountState());
                productEntity.get().setDateModified(LocalDateTime.now());
                productRepository.save(productEntity.get());
                return MessageApplication.UPDATEACCOUNTS;
            } else if (editAccountStatusDto.getAccountState() == AccountState.ACTIVE || editAccountStatusDto.getAccountState() == AccountState.INACTIVE) {
                productEntity.get().setAccountState(editAccountStatusDto.getAccountState());
                productEntity.get().setDateModified(LocalDateTime.now());
                productRepository.save(productEntity.get());
                return MessageApplication.UPDATEACCOUNTS;
            }
            return MessageApplication.VALUE_BALANCE_ZERO;
        }
        return MessageApplication.ACCOUNTNOTFOUND;
    }

    @Override
    public Object accountCanceled(EditAccountStatusDto editAccountStatusDto) {
        Optional<ProductEntity> productEntity = productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber());
        if (productEntity.isPresent()) {
            if (productEntity.get().getBalance().equals(BigDecimal.ZERO)) {
                productEntity.get().setAccountState(AccountState.CANCELLED);
                productEntity.get().setDateModified(LocalDateTime.now());
                productRepository.save(productEntity.get());
                return MessageApplication.ACCOUNTCANCELLED;
            } else {
                return MessageApplication.ACCOUNTMUSTHAVEZERO;
            }
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
    public Object transferMoney(String accountOrigin, String accountDestination, BigDecimal balance) {
        if (productRepository.existsByAccountNumber(accountDestination)){
            withdrawMoney(accountOrigin, balance);
            consignMoney(accountDestination, balance);
            return MessageApplication.TRANSFERSUCCESFULL;
        }return MessageApplication.VALUE_NUMBER_DESTINATION_EXIST;
    }
}
