package com.technical.test.quind.hexagonal.domain.port;

import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;

public interface ClientPersistencePort {
    Object createClient(ClientDto clientDto);

    Object updateClient(String identificationNumber, ClientDto clientDto);

    String deleteClient(String identificationNumber);
}
