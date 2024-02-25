package com.technical.test.quind.hexagonal.application.service;

import com.technical.test.quind.hexagonal.application.mapper.ClientMapper;
import com.technical.test.quind.hexagonal.application.usecases.ClientService;
import com.technical.test.quind.hexagonal.domain.model.constant.MessageApplication;
import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientManagementService implements ClientService {
    private final ClientRepository clientRepository;
    @Override
    public Object createClient(ClientDto clientDto) {
        Boolean ageValid = validateAgeClient(clientDto.getDateOfBirth());
        if (!ageValid) {
            return MessageApplication.NOMINORS;
        }
        clientDto.setDateCreated(LocalDateTime.now());
        clientDto.setDateModified(null);
        ClientEntity saveInformation = ClientMapper.dtoToClientEntity(clientDto);
        return clientRepository.save(saveInformation);
    }
    @Override
    public Object updateClient(String identificationNumber, ClientDto clientDto) {
        if (!(clientDto.getDateOfBirth().isEmpty())){
            Boolean ageValid = validateAgeClient(clientDto.getDateOfBirth());
            if (!ageValid){
                return MessageApplication.NOMINORS;
            }
        }
        return getFindClientEntity(clientDto);
    }
    @Override
    public String deleteClient(String identificationNumber) {
        Optional<ClientEntity> clientEntity = clientRepository.findClientEntityByIdentificationNumber(identificationNumber);
        if (clientEntity.isPresent()) {
            if (clientEntity.get().getProductEntities().isEmpty()) {
                clientRepository.deleteById(clientEntity.get().getId());
                return MessageApplication.DELETECLIENT;
            }
            return MessageApplication.DELETECLIENTERROR;
        }

        return MessageApplication.CLIENTNOTFOUND;
    }
    private Boolean validateAgeClient(String dateOfBirth) {
        LocalDate dateNac = LocalDate.parse(dateOfBirth);
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateNac, now);
        var age = period.getYears();
        var ageMinimum = 18;
        return age >= ageMinimum;
    }
    public Object getFindClientEntity(ClientDto clientDto){
        Optional<ClientEntity> existingClientEntity = clientRepository.findClientEntityByIdentificationNumber(clientDto.getIdentificationNumber());
        if (existingClientEntity.isPresent()){
            existingClientEntity.get().setIdentificationTypeEnum(clientDto.getIdentificationTypeEnum());
            existingClientEntity.get().setIdentificationNumber(clientDto.getIdentificationNumber());
            existingClientEntity.get().setClientName(clientDto.getClientName());
            existingClientEntity.get().setClientSurname(clientDto.getClientSurname());
            existingClientEntity.get().setClientEmail(clientDto.getClientEmail());
            existingClientEntity.get().setDateOfBirth(clientDto.getDateOfBirth());
            existingClientEntity.get().setDateModified(LocalDateTime.now());
            return clientRepository.save(existingClientEntity.get());
        }
        return MessageApplication.NULL;
    }
}
