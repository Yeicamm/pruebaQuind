package com.technical.test.quind.hexagonal.service;

import com.technical.test.quind.hexagonal.application.service.ClientManagementService;
import com.technical.test.quind.hexagonal.domain.model.constant.MessageApplication;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

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
    void createClient() {
        ClientDto clientDto = ClientDto.builder()
                .dateOfBirth("2000-01-01")
                .build();

        ClientEntity clientEntity = new ClientEntity();
        Mockito.when(clientRepository.save(clientEntity)).thenReturn(clientEntity);
        clientService.createClient(clientDto);
    }
    @Test
    void createClientMinor() {
        ClientDto clientDto = ClientDto.builder()
                .dateOfBirth("2020-01-01")
                .build();
        clientService.createClient(clientDto);
    }
    @Test
    void updateClient(){
        ClientDto clientDto = ClientDto.builder()
                .identificationNumber("1000381834")
                .dateOfBirth("2003-09-03")
                .build();
        ClientEntity clientEntity = new ClientEntity();
        Mockito.when(clientRepository.findClientEntityByIdentificationNumber(clientDto.getIdentificationNumber())).thenReturn(Optional.of(clientEntity));
        Mockito.when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);
        clientService.updateClient(clientDto.getIdentificationNumber(), clientDto);
    }
    @Test
    void updateClientMinor() {
        ClientDto clientDto = ClientDto.builder()
                .identificationNumber("1000381834")
                .dateOfBirth("2021-09-03")
                .build();
        clientService.updateClient(clientDto.getIdentificationNumber(),clientDto);
    }
    @Test
    void updateBirthDayEmpty() {
        ClientDto clientDto = ClientDto.builder()
                .identificationNumber("1000381834")
                .dateOfBirth("")
                .build();
        clientService.updateClient(clientDto.getIdentificationNumber(),clientDto);
    }
    @Test
    void deleteClientIsPresentProduct() {
        ClientDto clientDto = ClientDto.builder()
                .identificationNumber("1000381834")
                .build();

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);

        List<ProductEntity> products = new ArrayList<>();
        products.add(new ProductEntity());
        clientEntity.setProductEntities(products);

        Mockito.when(clientRepository.findClientEntityByIdentificationNumber(clientDto.getIdentificationNumber()))
                .thenReturn(Optional.of(clientEntity));

        Mockito.when(clientRepository.findClientEntityByIdentificationNumber(""))
                .thenReturn(Optional.empty());

        assertEquals(MessageApplication.DELETECLIENTERROR, clientService.deleteClient(clientDto.getIdentificationNumber()));


        assertEquals(MessageApplication.CLIENTNOTFOUND, clientService.deleteClient(""));
    }
}
