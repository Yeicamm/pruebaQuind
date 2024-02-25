package com.technical.test.quind.hexagonal.infrastructure.rest.controller;

import com.technical.test.quind.hexagonal.application.usecases.ClientService;
import com.technical.test.quind.hexagonal.domain.model.constant.MessageAplication;
import com.technical.test.quind.hexagonal.domain.model.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {


    private final ClientService clientService;


    @PostMapping("/create")
    public String createClient(@RequestBody ClientDto clientDto){
        try {
            clientService.createClient(clientDto);
            return MessageAplication.ACCOUNTCREATED;
        } catch (Exception e) {
            return MessageAplication.CANCELLED;
        }
    }

    @PatchMapping("/update/{identificationNumber}")
    public String updateClient(@PathVariable String identificationNumber, @RequestBody ClientDto clientDto){
        try {
            clientService.updateClient(identificationNumber, clientDto);
            return MessageAplication.UPDATEACCOUNTS;
        } catch (IllegalArgumentException e) {
            return MessageAplication.ACCOUNTNOTFOUND;
        } catch (Exception e) {
            return MessageAplication.ACCOUNTCANCELLED;
        }
    }

    @DeleteMapping("/delete/{identificationNumber}")
    public String deleteClient(@PathVariable String identificationNumber){
        try {
            clientService.deleteClient(identificationNumber);
            return MessageAplication.ACCOUNTCANCELLED;
        } catch (IllegalArgumentException e) {
            return MessageAplication.ACCOUNTNOTFOUND;
        } catch (Exception e) {
            return MessageAplication.ACCOUNTCANCELLED;
        }
    }
}
