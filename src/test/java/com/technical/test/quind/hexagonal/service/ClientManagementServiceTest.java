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
import org.mockito.MockitoAnnotations;

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
    void createCliente() {
        ClientDto clientDto = ClientDto.builder()
                .dateOfBirth("2000-11-11")
                .build();

        ClientEntity clienteEntity = new ClientEntity();
        when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
        clientService.createClient(clientDto);
    }
}
