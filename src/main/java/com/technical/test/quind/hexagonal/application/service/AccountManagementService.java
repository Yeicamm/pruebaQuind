package com.technical.test.quind.hexagonal.application.service;

import com.technical.test.quind.hexagonal.application.mapper.ProductMapper;
import com.technical.test.quind.hexagonal.domain.model.constant.MessageApplication;
import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;
import com.technical.test.quind.hexagonal.domain.model.enums.AccountState;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;
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

    public Object createSavingsAccount(ProductDto productDto) {
        int balance = 0;
        if (productDto.getBalance().compareTo(BigDecimal.ZERO) < balance) {
            return MessageApplication.BALANCECANNOT;
        }
        saveSavingsProductRepository(productDto);

        return MessageApplication.ACCOUNTCREATED;
    }

    public Object createCurrentAccount(ProductDto productDto) {
        saveCurrentProductRepository(productDto);
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

    public void saveSavingsProductRepository(ProductDto productDto) {
        ProductEntity product = ProductMapper.dtoToProductEntity(productDto);
        productDto.setAccountNumber(null);
        productDto.setAccountNumber(generateNumberAccountRandom("53"));
        productDto.setAccountState(AccountState.ACTIVE);
        productDto.setDateCreated(LocalDateTime.now());
        productRepository.save(product);
    }

    public void saveCurrentProductRepository(ProductDto productDto) {
        productDto.setAccountNumber(null);

        requestAccountClientDto.getProductDto().setAccountNumber(generateNumberAccountRandom("33"));
        requestAccountClientDto.getProductDto().setAccountState(requestAccountClientDto.getProductDto().getAccountState());
        requestAccountClientDto.getProductDto().setDateCreated(LocalDateTime.now());

        ProductEntity product = ProductMapper.dtoToProductEntity(requestAccountClientDto.getProductDto());
        productRepository.save(product);
    }

}
