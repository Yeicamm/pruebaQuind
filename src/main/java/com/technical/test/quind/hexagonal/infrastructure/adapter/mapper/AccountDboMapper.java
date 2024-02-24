package com.technical.test.quind.hexagonal.infrastructure.adapter.mapper;


import com.technical.test.quind.hexagonal.domain.model.Account;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.AccountEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountDboMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "accountType",target = "accountType")
    @Mapping(source = "product_id",target = "product_id")
    AccountEntity toDbo(Account domain);

    @InheritInverseConfiguration
    Account toDomain(AccountEntity accountEntity);
}
