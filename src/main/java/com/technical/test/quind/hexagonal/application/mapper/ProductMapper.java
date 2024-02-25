package com.technical.test.quind.hexagonal.application.mapper;

import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;

public class ProductMapper {
    public static ProductEntity dtoToProductEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setAccountState(productDto.getAccountState());
        productEntity.setBalance(productDto.getBalance());
        productEntity.setAccountNumber(productDto.getAccountNumber());
        productEntity.setClientEntity(ClientEntity
                .builder()
                .id(productDto.getClientId())
                .build());
        productEntity.setDateCreated(productDto.getDateCreated());
        productEntity.setGmfExempt(productDto.isGmfExempt());
        return productEntity;
    }
}
