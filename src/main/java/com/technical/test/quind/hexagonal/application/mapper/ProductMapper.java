package com.technical.test.quind.hexagonal.application.mapper;

import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;
import com.technical.test.quind.hexagonal.domain.model.enums.AccountState;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;

import java.time.LocalDateTime;

public class ProductMapper {
    public static ProductEntity dtoToProductEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setAccountType(productDto.getAccountTypes());
        productEntity.setAccountState(AccountState.ACTIVE);
        productEntity.setBalance(productDto.getBalance());
        productEntity.setAccountNumber(productDto.getAccountNumber());
        productEntity.setClientEntity(ClientEntity
                .builder()
                .id(productDto.getClientId())
                .build());
        productEntity.setDateCreated(LocalDateTime.now());
        productEntity.setDateModified(null);
        productEntity.setGmfExempt(productDto.isGmfExempt());
        return productEntity;
    }
}
