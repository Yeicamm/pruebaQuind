package com.technical.test.quind.hexagonal.application.mapper;

import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ClientMapper {
    public static ClientEntity dtoToClientEntity(ClientDto clientDTO) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setIdentificationTypeEnum(clientDTO.getIdentificationTypeEnum());
        clientEntity.setIdentificationNumber(clientDTO.getIdentificationNumber());
        clientEntity.setClientName(clientDTO.getClientName());
        clientEntity.setClientSurname(clientDTO.getClientSurname());
        clientEntity.setClientEmail(clientDTO.getClientEmail());
        clientEntity.setDateOfBirth(clientDTO.getDateOfBirth());
        clientEntity.setDateCreated(LocalDateTime.now());
        clientEntity.setDateModified(null);
        return clientEntity;
    }
}
