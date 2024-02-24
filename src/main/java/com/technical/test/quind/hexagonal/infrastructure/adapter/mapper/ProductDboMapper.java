package com.technical.test.quind.hexagonal.infrastructure.adapter.mapper;

import com.technical.test.quind.hexagonal.domain.model.Product;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductDboMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "accountNumber",target = "accountNumber")
    @Mapping(source = "accountState",target = "accountState")
    @Mapping(source = "balance",target = "balance")
    @Mapping(source = "gmfExempt", target = "gmfExempt")
    @Mapping(source = "account", target = "account")
    @Mapping(source = "clientEntity",target = "clientEntity")
    ProductEntity toDbo(Product domain);

    @InheritInverseConfiguration
    Product toDomain(ProductEntity productEntity);



}
