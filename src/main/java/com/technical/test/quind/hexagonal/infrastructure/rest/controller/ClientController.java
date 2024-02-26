package com.technical.test.quind.hexagonal.infrastructure.rest.controller;

import com.technical.test.quind.hexagonal.application.usecases.ClientService;
import com.technical.test.quind.hexagonal.domain.model.constant.MessageApplication;
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
    public String updateClient(@PathVariable String identificationNumber, @RequestBody ClientDto clientDto){
        try {
            clientService.updateClient(identificationNumber, clientDto);
            return MessageApplication.UPDATEACCOUNTS;
        } catch (IllegalArgumentException e) {
            return MessageApplication.ACCOUNTNOTFOUND;
        } catch (Exception e) {
            return MessageApplication.ACCOUNTCANCELLED;
        }
    }

    @DeleteMapping("/delete/{identificationNumber}")
    public String deleteClient(@PathVariable String identificationNumber){
        try {
            clientService.deleteClient(identificationNumber);
            return MessageApplication.DELETECLIENT;
        } catch (IllegalArgumentException e) {
            return MessageApplication.ACCOUNTNOTFOUND;
        } catch (Exception e) {
            return MessageApplication.ACCOUNTCANCELLED;
        }
    }
}
