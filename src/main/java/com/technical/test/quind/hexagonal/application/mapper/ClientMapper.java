package com.technical.test.quind.hexagonal.application.mapper;

import com.technical.test.quind.hexagonal.domain.model.Account;
import com.technical.test.quind.hexagonal.domain.model.Client;
import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.AccountEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface ClientMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "identificationTypeEnum", target = "identificationTypeEnum")
    @Mapping(source = "identificationNumber", target = "identificationNumber")
    @Mapping(source = "clientName", target = "clientName")
    @Mapping(source = "clientSurname", target = "clientSurname")
    @Mapping(source = "clientEmail", target = "clientEmail")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "productEntities", target = "productEntities")
    ClientEntity toDbo(ClientDto domain);

    @InheritInverseConfiguration
    Account toDomain(AccountEntity accountEntity);
}
