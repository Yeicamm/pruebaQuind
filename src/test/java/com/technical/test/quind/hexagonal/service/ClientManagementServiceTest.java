package com.technical.test.quind.hexagonal.service;

import com.technical.test.quind.hexagonal.application.mapper.ClientMapper;
import com.technical.test.quind.hexagonal.application.service.ClientManagementService;
import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientManagementServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientManagementService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validationNameCreate(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("a")
                .clientSurname("Rojas")
                .build();
        clientService.createClient(clientDto);
    }
    @Test
    void validationSurnameCreate(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cristian")
                .clientSurname("a")
                .build();
        clientService.createClient(clientDto);
    }
    @Test
    void nameNullCreate(){
        ClientDto clientDto = ClientDto.builder()
                .clientName(null)
                .clientSurname("Rojas")
                .build();
        clientService.createClient(clientDto);
    }
    @Test
    void surnameNullCreate(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cristian")
                .clientSurname(null)
                .build();
        clientService.createClient(clientDto);
    }
    @Test
    void EmailValidatorInvalidCreate(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cristian")
                .clientSurname("Rojas")
                .clientEmail("a")
                .build();
        clientService.createClient(clientDto);
    }
    @Test
    void AgeInvalid(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cristian")
                .clientSurname("Rojas")
                .clientEmail("aaaaa@aaaa.com")
                .dateOfBirth("2020-01-01")
                .build();
        clientService.createClient(clientDto);
    }
    @Test
    void createAccount(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cristian")
                .clientSurname("Rojas")
                .clientEmail("aaaaa@aaaa.com")
                .dateOfBirth("2000-01-01")
                .build();
        ClientEntity saveInformation = ClientMapper.dtoToClientEntity(clientDto);
        Mockito.when(clientRepository.save(saveInformation)).thenReturn(saveInformation);
        clientService.createClient(clientDto);
    }
    @Test
    void validationNameUpdate(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("a")
                .clientSurname("Rojas")
                .build();
        clientService.updateClient("1000381834",clientDto);
    }
    @Test
    void validationSurnameUpdate(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cristian")
                .clientSurname("a")
                .build();
        clientService.updateClient("1000381834",clientDto);
    }
    @Test
    void nameNullUpdate(){
        ClientDto clientDto = ClientDto.builder()
                .clientName(null)
                .clientSurname("Rojas")
                .build();
        clientService.updateClient("1000381834",clientDto);
    }
    @Test
    void surnameNullUpdate(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cristian")
                .clientSurname(null)
                .build();
        clientService.updateClient("1000381834",clientDto);
    }
    @Test
    void EmailValidatorInvalidUpdate(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cristian")
                .clientSurname("Rojas")
                .clientEmail("a")
                .build();
        clientService.updateClient("1000381834",clientDto);
    }
    @Test
    void ageInvalidUpdate(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cristian")
                .clientSurname("Rojas")
                .clientEmail("aaaaa@aaaa.com")
                .dateOfBirth("2020-01-01")
                .build();
        clientService.updateClient("1000381834",clientDto);
    }
    @Test
    void updateClient(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cristian")
                .clientSurname("Rojas")
                .clientEmail("aaaaa@aaaa.com")
                .dateOfBirth("2000-01-01")
                .build();
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setIdentificationNumber("1000381834");
        Mockito.when(clientRepository.findClientEntityByIdentificationNumber(clientEntity.getIdentificationNumber())).thenReturn(Optional.of(clientEntity));
        clientService.updateClient("1000381834",clientDto);
    }
    @Test
    void updateClientIsPresent(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cristian")
                .clientSurname("Rojas")
                .clientEmail("aaaaa@aaaa.com")
                .dateOfBirth("2000-01-01")
                .identificationNumber("1000381834")
                .build();
        Optional<ClientEntity> clientEntity = clientRepository.findClientEntityByIdentificationNumber(clientDto.getIdentificationNumber());
        Mockito.when(clientRepository.findClientEntityByIdentificationNumber(clientDto.getIdentificationNumber())).thenReturn(clientEntity);
        clientService.updateClient("1000381834",clientDto);
    }
    @Test
    void birthDayEmpty(){
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cristian")
                .clientSurname("Rojas")
                .clientEmail("aaaaa@aaaa.com")
                .dateOfBirth("")
                .build();
        clientService.updateClient("1000381834",clientDto);
    }
    @Test
    void deleteOptionalIsPresent(){
        ClientEntity clientEntity = new ClientEntity();
        Optional<ClientEntity> clientEntity2 = clientRepository.findClientEntityByIdentificationNumber(clientEntity.getIdentificationNumber());
        clientService.deleteClient("1000381834");
    }
    @Test
    void deleteProductEntitiesEmpty(){
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setProductEntities(List.of());
        clientEntity.setIdentificationNumber("1000381834");
        Mockito.when(clientRepository.findClientEntityByIdentificationNumber(clientEntity.getIdentificationNumber())).thenReturn(Optional.of(clientEntity));
        clientService.deleteClient("1000381834");
    }
    @Test
    void deleteProductEntitiesNoEmpty(){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setAccountNumber("53");
        List<ProductEntity> product = new ArrayList<>();
        product.add(productEntity);
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setProductEntities(product);
        clientEntity.setIdentificationNumber("1000381834");
        Mockito.when(clientRepository.findClientEntityByIdentificationNumber(clientEntity.getIdentificationNumber())).thenReturn(Optional.of(clientEntity));
        clientService.deleteClient("1000381834");
    }

}
