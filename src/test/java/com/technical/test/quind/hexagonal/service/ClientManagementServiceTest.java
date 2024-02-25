package com.technical.test.quind.hexagonal.service;

import com.technical.test.quind.hexagonal.application.mapper.ClientMapper;
import com.technical.test.quind.hexagonal.application.service.ClientManagementService;
import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import com.technical.test.quind.hexagonal.infrastructure.adapter.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientManagementServiceTest {


    @Mock
    private ClientRepository clienteRepository;

    @Mock
    private ClientMapper clientMapper;

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

        ClientEntity clienteEntity = new ClientEntity();
        when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
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
        Mockito.when(clienteRepository.findClientEntityByIdentificationNumber(clientDto.getIdentificationNumber())).thenReturn(Optional.of(clientEntity));
        Mockito.when(clienteRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);
        clientService.updateClient(clientDto.getIdentificationNumber(), clientDto);
    }
}
