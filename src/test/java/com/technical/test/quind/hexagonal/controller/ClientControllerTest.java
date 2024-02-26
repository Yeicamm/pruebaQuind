package com.technical.test.quind.hexagonal.controller;

import com.technical.test.quind.hexagonal.application.usecases.ClientService;
import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;
import com.technical.test.quind.hexagonal.infrastructure.rest.controller.ClientController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {
    @Mock
    private  ClientService clientService;
    @InjectMocks
    private ClientController clientController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createClient() {
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cris")
                .clientSurname("Roja")
                .clientEmail("ccc@co.com")
                .dateOfBirth("2000-01-01")
                .identificationTypeEnum("CC")
                .build();
        clientController.createClient(clientDto);
    }
    @Test
    void updateClient() {
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cris")
                .clientSurname("Roja")
                .clientEmail("ccc@co.com")
                .dateOfBirth("2000-01-01")
                .identificationTypeEnum("CC")
                .build();
        clientController.updateClient(clientDto.getIdentificationNumber(),clientDto);
    }
    @Test
    void deleteClient() {
        ClientDto clientDto = ClientDto.builder()
                .clientName("Cris")
                .clientSurname("Roja")
                .clientEmail("ccc@co.com")
                .dateOfBirth("2000-01-01")
                .identificationTypeEnum("CC")
                .build();
        clientController.deleteClient(clientDto.getIdentificationNumber());
    }
}
