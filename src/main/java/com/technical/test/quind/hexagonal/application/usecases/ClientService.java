package com.technical.test.quind.hexagonal.application.usecases;

import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;

public interface ClientService {
    Object createClient(ClientDto clientDto);

    Object updateClient(String identificationNumber, ClientDto clientDto);

    void deleteClient(Long identificationNumber);
}
