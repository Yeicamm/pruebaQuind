package com.technical.test.quind.hexagonal.application.service;

import com.technical.test.quind.hexagonal.application.mapper.ClientMapper;
import com.technical.test.quind.hexagonal.application.usecases.ClientService;
import com.technical.test.quind.hexagonal.domain.model.constant.MessageApplication;
import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.validation.EmailValidator;
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
        if (!(isInvalidUseName(clientDto) || isInvalidUseSurname(clientDto))){
            if (EmailValidator.isValidEmail(clientDto.getClientEmail())){
                Boolean ageValid = validateAgeClient(clientDto.getDateOfBirth());
                if (!ageValid) {
                    return MessageApplication.NO_MINORS;
                }else {
                    clientDto.setDateCreated(LocalDateTime.now());
                    clientDto.setDateModified(null);
                    ClientEntity saveInformation = ClientMapper.dtoToClientEntity(clientDto);
                    clientRepository.save(saveInformation);
                    return MessageApplication.ACCOUNT_CREATED;
                }
            }
            return MessageApplication.STRUCTURE_EMAIL;
        }
        return MessageApplication.LENGTH_NAME_SURNAME;
    }
    @Override
    public Object updateClient(String identificationNumber, ClientDto clientDto) {
        Optional<ClientEntity> clientEntity = clientRepository.findClientEntityByIdentificationNumber(identificationNumber);
        if (!(isInvalidUseName(clientDto) || isInvalidUseSurname(clientDto))){
            if (EmailValidator.isValidEmail(clientDto.getClientEmail())){
                if (!(clientDto.getDateOfBirth().isEmpty())){
                    Boolean ageValid = validateAgeClient(clientDto.getDateOfBirth());
                    if (!ageValid){
                        return MessageApplication.NO_MINORS;
                    }
                    if (clientEntity.isPresent()){
                        clientEntity.get().setIdentificationTypeEnum(clientDto.getIdentificationTypeEnum());
                        clientEntity.get().setIdentificationNumber(clientDto.getIdentificationNumber());
                        clientEntity.get().setClientName(clientDto.getClientName());
                        clientEntity.get().setClientSurname(clientDto.getClientSurname());
                        clientEntity.get().setClientEmail(clientDto.getClientEmail());
                        clientEntity.get().setDateOfBirth(clientDto.getDateOfBirth());
                        clientEntity.get().setDateModified(LocalDateTime.now());
                        clientRepository.save(clientEntity.get());
                        return MessageApplication.CLIENT_UPDATE;
                    }
                }
            }
            return MessageApplication.STRUCTURE_EMAIL;
        }
        return MessageApplication.LENGTH_NAME_SURNAME;
    }
    @Override
    public String deleteClient(String identificationNumber) {
        Optional<ClientEntity> clientEntity = clientRepository.findClientEntityByIdentificationNumber(identificationNumber);
        if (clientEntity.isPresent()) {
            if (clientEntity.get().getProductEntities().isEmpty()) {
                clientRepository.deleteById(clientEntity.get().getId());
                return MessageApplication.DELETE_CLIENT;
            }
            return MessageApplication.DELETE_CLIENT_ERROR;
        }
        return MessageApplication.CLIENT_NOTFOUND;
    }
    private Boolean validateAgeClient(String dateOfBirth) {
        LocalDate dateNac = LocalDate.parse(dateOfBirth);
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateNac, now);
        var age = period.getYears();
        var ageMinimum = 18;
        return age >= ageMinimum;
    }
    public boolean isInvalidUseName(ClientDto clientDto){
        return clientDto.getClientName() == null || clientDto.getClientName().length() < 2;
    }
    public boolean isInvalidUseSurname(ClientDto clientDto){
        return clientDto.getClientSurname() == null || clientDto.getClientSurname().length() < 2;
    }
}
