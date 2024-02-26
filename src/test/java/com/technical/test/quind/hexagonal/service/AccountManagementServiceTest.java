package com.technical.test.quind.hexagonal.service;

import com.technical.test.quind.hexagonal.application.service.AccountManagementService;
import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.repository.ClientRepository;
import com.technical.test.quind.hexagonal.infrastructure.adapter.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;

public class AccountManagementServiceTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    AccountManagementService accountManagementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


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
        Mockito.when(productRepository.existsByAccountNumber("331000")).thenReturn(true);
        accountManagementService.generateNumberAccountRandom("33"+"1000");
    }
}
