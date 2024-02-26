package com.technical.test.quind.hexagonal.controller;

import com.technical.test.quind.hexagonal.application.usecases.ClientService;
import com.technical.test.quind.hexagonal.application.usecases.ProductService;
import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;
import com.technical.test.quind.hexagonal.domain.model.dto.EditAccountStatusDto;
import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import com.technical.test.quind.hexagonal.infrastructure.rest.controller.ClientController;
import com.technical.test.quind.hexagonal.infrastructure.rest.controller.ProductController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createAccount() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        ProductDto productDto = ProductDto.builder()
                .clientId(clientEntity.getId())
                .balance(BigDecimal.valueOf(1))
                .accountType("SAVINGS")
                .build();
        productController.createAccount(productDto);
    }
    @Test
    void updateAccountState() {
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountNumber("5312345678")
                .build();
        productController.updateAccountState(editAccountStatusDto);
    }
    @Test
    void accountCanceled() {
        EditAccountStatusDto editAccountStatusDto = EditAccountStatusDto.builder()
                .accountNumber("5312345678")
                .build();
        productController.accountCanceled(editAccountStatusDto);
    }
    @Test
    void consign() {
        productController.consign("53000",BigDecimal.valueOf(50000));
    }
    @Test
    void withdrawMoney() {
        productController.withdrawMoney("53000",BigDecimal.valueOf(50000));
    }
    @Test
    void transferMoney() {
        productController.transferMoney("53000","530000000",BigDecimal.valueOf(50000));
    }
}
