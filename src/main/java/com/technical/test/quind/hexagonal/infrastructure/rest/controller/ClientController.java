package com.technical.test.quind.hexagonal.infrastructure.rest.controller;

import com.technical.test.quind.hexagonal.application.usecases.ClientService;
import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {


    private final ClientService clientService;

    @PostMapping("/create")
    public Object createClient(@RequestBody ClientDto clientDto){
        return clientService.createClient(clientDto);
    }

    @PatchMapping("/update/{identificationNumber}")
    public Object updateClient(@PathVariable String identificationNumber, @RequestBody ClientDto clientDto){
        return clientService.updateClient(identificationNumber,clientDto);
    }

    @DeleteMapping("/delete/{identificationNumber}")
    public String deleteClient(@PathVariable String identificationNumber){
        return clientService.deleteClient(identificationNumber);
    }
}
