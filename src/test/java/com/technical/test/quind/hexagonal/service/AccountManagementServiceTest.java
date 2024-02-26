package com.technical.test.quind.hexagonal.service;

import com.technical.test.quind.hexagonal.application.service.AccountManagementService;
import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.BreakIterator;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AccountManagementServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private AccountManagementService accountManagementService;

    @Test
    void createSavingsAccountNegative() {
        ProductDto productDto = ProductDto.builder()
                .balance(BigDecimal.valueOf(-11000))
                .build();
        accountManagementService.createSavingsAccount(productDto);
    }
    @Test
    void createSavingsAccountPositive() {
        ProductDto productDto = ProductDto.builder()
                .balance(BigDecimal.valueOf(11000))
                .build();
        accountManagementService.createSavingsAccount(productDto);
    }
    @Test
    void createCurrentAccountPositive() {
        ProductDto productDto = ProductDto.builder()
                .balance(BigDecimal.valueOf(11000))
                .build();
        accountManagementService.createCurrentAccount(productDto);
    }
    @Test
    void generateRandomNumber() {
        Mockito.when(productRepository.existsByAccountNumber(any())).thenReturn(false);
        accountManagementService.generateNumberAccountRandom("53");
    }
    @Test
    void generateRandomNumberD() {
        Mockito.when(productRepository.existsByAccountNumber(Mockito.any())).thenReturn(true);
        accountManagementService.generateNumberAccountRandom("53");

    }
}
