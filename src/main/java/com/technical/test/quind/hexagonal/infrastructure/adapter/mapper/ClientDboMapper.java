package com.technical.test.quind.hexagonal.infrastructure.adapter.mapper;

import com.technical.test.quind.hexagonal.domain.model.Client;
import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientDboMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "identificationTypeEnum", target = "identificationTypeEnum")
    @Mapping(source = "clientName", target = "clientName")
    @Mapping(source = "clientSurname", target = "clientSurname")
    @Mapping(source = "client_email", target = "clientEmail")
    @Mapping(source = "date_of_birth", target = "dateOfBirth")
    @Mapping(source = "productEntities", target = "productEntities")
    ClientEntity toDbo (Client domain);

    @InheritInverseConfiguration
    Client toDomain(ClientEntity entity);
}
