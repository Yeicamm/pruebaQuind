package com.technical.test.quind.hexagonal.application.service;

import com.technical.test.quind.hexagonal.application.mapper.ProductMapper;
import com.technical.test.quind.hexagonal.domain.model.constant.MessageApplication;
import com.technical.test.quind.hexagonal.domain.model.dto.request.RequestAccountClientDto;
import com.technical.test.quind.hexagonal.domain.model.enums.AccountState;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.AccountEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.repository.AccountRepository;
import com.technical.test.quind.hexagonal.infrastructure.adapter.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class AccountManagementService {
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    public Object createSavingsAccount(RequestAccountClientDto requestAccountClientDto) {
        int balance = 0;
        if (requestAccountClientDto.getProductDto().getBalance().compareTo(BigDecimal.ZERO) < balance) {
            return MessageApplication.BALANCECANNOT;
        }
        saveSavingsProductRepository(requestAccountClientDto);
        saveAccountRepository(requestAccountClientDto);
        return MessageApplication.ACCOUNTCREATED;
    }

    public Object createCurrentAccount(RequestAccountClientDto requestAccountClientDto) {
        saveCurrentProductRepository(requestAccountClientDto);
        saveAccountRepository(requestAccountClientDto);
        return MessageApplication.ACCOUNTCREATED;
    }

    public String generateNumberAccountRandom(String prefix) {
        Random random = new Random();
        int randomNumber = 10000000 + random.nextInt(90000000);

        while (productRepository.existsByAccountNumber(prefix + randomNumber)) {
            randomNumber = 10000000 + random.nextInt(90000000);
        }
        return prefix + randomNumber;
    }

    public void saveSavingsProductRepository(RequestAccountClientDto requestAccountClientDto) {
        requestAccountClientDto.getProductDto().setAccountNumber(null);
        requestAccountClientDto.getProductDto().setAccountNumber(generateNumberAccountRandom("53"));
        requestAccountClientDto.getProductDto().setAccountState(AccountState.ACTIVE);
        requestAccountClientDto.getProductDto().setDateCreated(LocalDateTime.now());

        ProductEntity product = ProductMapper.dtoToProductEntity(requestAccountClientDto.getProductDto());
        productRepository.save(product);
    }

    public void saveCurrentProductRepository(RequestAccountClientDto requestAccountClientDto) {
        requestAccountClientDto.getProductDto().setAccountNumber(null);

        requestAccountClientDto.getProductDto().setAccountNumber(generateNumberAccountRandom("33"));
        requestAccountClientDto.getProductDto().setAccountState(requestAccountClientDto.getProductDto().getAccountState());
        requestAccountClientDto.getProductDto().setDateCreated(LocalDateTime.now());

        ProductEntity product = ProductMapper.dtoToProductEntity(requestAccountClientDto.getProductDto());
        productRepository.save(product);
    }

    public void saveAccountRepository(RequestAccountClientDto requestAccountClientDto) {
        ProductEntity product = ProductMapper.dtoToProductEntity(requestAccountClientDto.getProductDto());
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountType(requestAccountClientDto.getAccountType());
        accountEntity.setProductEntity(product);
        accountRepository.save(accountEntity);
    }
}
