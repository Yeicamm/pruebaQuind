package com.technical.test.quind.hexagonal.service;

import com.technical.test.quind.hexagonal.application.service.AccountManagementService;
import com.technical.test.quind.hexagonal.application.service.ProductManagementService;
import com.technical.test.quind.hexagonal.domain.model.dto.EditAccountStatusDto;
import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;
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
        ProductDto requestAccountClientDto = ProductDto.builder()
                .accountType("")
                .build();
        productManagementService.accountCreate(requestAccountClientDto);

    }
    @Test
    void createAccountSavings(){
        ProductDto productDto = ProductDto.builder()
                .accountType("SAVINGS")
                .build();
        productManagementService.accountCreate(productDto);
    }
    @Test
    void createAccountCurrent(){
        ProductDto requestAccountClientDto = ProductDto.builder()
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
    @Test
    void consignMoneyNegative(){
        var accountNumber = "5300000001";
        var balance = BigDecimal.valueOf(-53);
        productManagementService.consignMoney(accountNumber,balance);
    }
    @Test
    void consignMoneyIsEmpty(){
        var accountNumber = "";
        var balance = BigDecimal.valueOf(1000);
        Mockito.when(productRepository.findProductEntityByAccountNumber(accountNumber)).thenReturn(Optional.empty());
        productManagementService.consignMoney(accountNumber,balance);
    }
    @Test
    void consignMoney(){
        var accountNumber = "5300000001";
        var balance = BigDecimal.valueOf(1000);
        ProductEntity product = new ProductEntity();
        product.setAccountNumber(accountNumber);
        product.setBalance(balance);
        Mockito.when(productRepository.findProductEntityByAccountNumber(accountNumber)).thenReturn(Optional.of(product));
        productManagementService.consignMoney(accountNumber,balance);
    }
    @Test
    void WithdrawMoneyIsEmpty(){
        ProductEntity product = new ProductEntity();
        Mockito.when(productRepository.findProductEntityByAccountNumber(product.getAccountNumber())).thenReturn(Optional.empty());
        productManagementService.withdrawMoney("",BigDecimal.valueOf(1));
    }
    @Test
    void WithdrawMoneyCompareTo() {
        ProductEntity product = new ProductEntity();
        product.setAccountNumber("53");
        product.setBalance(BigDecimal.valueOf(5000));
        Mockito.when(productRepository.findProductEntityByAccountNumber("53")).thenReturn(Optional.of(product));
        productManagementService.withdrawMoney("53",BigDecimal.valueOf(4000));
    }
    @Test
    void WithdrawMoneyInsufficientBalance() {
        ProductEntity product = new ProductEntity();
        product.setAccountNumber("53");
        product.setBalance(BigDecimal.valueOf(10));
        Mockito.when(productRepository.findProductEntityByAccountNumber(product.getAccountNumber())).thenReturn(Optional.of(product));
        productManagementService.withdrawMoney("53",BigDecimal.valueOf(4000));
    }
    @Test
    void transferMoney(){
        productManagementService.transferMoney("5300000","53102102",BigDecimal.valueOf(20000));
    }
}
