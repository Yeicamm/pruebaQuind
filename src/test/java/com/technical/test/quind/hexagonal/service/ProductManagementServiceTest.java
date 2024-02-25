package com.technical.test.quind.hexagonal.service;

import com.technical.test.quind.hexagonal.application.mapper.ProductMapper;
import com.technical.test.quind.hexagonal.application.service.AccountManagementService;
import com.technical.test.quind.hexagonal.application.service.ProductManagementService;
import com.technical.test.quind.hexagonal.domain.model.dto.EditAccountStatusDto;
import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;
import com.technical.test.quind.hexagonal.domain.model.dto.request.RequestAccountClientDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.repository.AccountRepository;
import com.technical.test.quind.hexagonal.infrastructure.adapter.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

public class ProductManagementServiceTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    private AccountManagementService accountManagementService;
    @InjectMocks
    private ProductManagementService productManagementService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cannotCreateAccountSavings(){
        ProductDto productDto = new ProductDto();
        RequestAccountClientDto requestAccountClientDto = RequestAccountClientDto.builder()
                .productDto(productDto)
                .accountType("")
                .build();
        productManagementService.accountCreate(requestAccountClientDto);

    }
    @Test
    void createAccountSavings(){
        ProductDto productDto = new ProductDto();
        RequestAccountClientDto requestAccountClientDto = RequestAccountClientDto.builder()
                .productDto(productDto)
                .accountType("SAVINGS")
                .build();
        productManagementService.accountCreate(requestAccountClientDto);
    }
    @Test
    void createAccountCurrent(){
        ProductDto productDto = new ProductDto();
        RequestAccountClientDto requestAccountClientDto = RequestAccountClientDto.builder()
                .productDto(productDto)
                .accountType("CURRENT")
                .build();
        productManagementService.accountCreate(requestAccountClientDto);
    }
    @Test
    void accountStateUpdateNotFound(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountNumber("53")
                .build();
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.empty());
        productManagementService.accountStateUpdate(editAccountStatusDto);
    }
    @Test
    void accountStateUpdateIsPresent(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountNumber("5312345678")
                .build();
        ProductEntity product = new ProductEntity();
        product.setAccountNumber("5312345642");
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.of(product));
        productManagementService.accountStateUpdate(editAccountStatusDto);
    }
    @Test
    void accountCanceledIsPresent(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountNumber("5312345678")
                .build();
        ProductEntity product = new ProductEntity();
        product.setBalance(BigDecimal.valueOf(1));
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.of(product));
        productManagementService.accountCanceled(editAccountStatusDto);
    }
    @Test
    void accountCanceledIsPresentCompareTo(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountNumber("5312345678")
                .build();
        ProductEntity product = new ProductEntity();
        product.setBalance(BigDecimal.valueOf(0));
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.of(product));
        productManagementService.accountCanceled(editAccountStatusDto);
    }
    @Test
    void accountCanceledNotFound(){
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountNumber("530000000")
                .build();
        Mockito.when(productRepository.findProductEntityByAccountNumber(editAccountStatusDto.getAccountNumber())).thenReturn(Optional.empty());
        productManagementService.accountCanceled(editAccountStatusDto);
    }
}
