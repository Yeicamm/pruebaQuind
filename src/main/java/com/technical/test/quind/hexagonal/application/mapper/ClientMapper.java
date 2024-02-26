package com.technical.test.quind.hexagonal.application.mapper;

import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ClientMapper {
    public static ClientEntity dtoToClientEntity(ClientDto clientDto) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setIdentificationTypeEnum(clientDto.getIdentificationTypeEnum());
        clientEntity.setIdentificationNumber(clientDto.getIdentificationNumber());
        clientEntity.setClientName(clientDto.getClientName());
        clientEntity.setClientSurname(clientDto.getClientSurname());
        clientEntity.setClientEmail(clientDto.getClientEmail());
        clientEntity.setDateOfBirth(clientDto.getDateOfBirth());
        clientEntity.setDateCreated(LocalDateTime.now());
        clientEntity.setDateModified(null);
        return clientEntity;
    }
}
