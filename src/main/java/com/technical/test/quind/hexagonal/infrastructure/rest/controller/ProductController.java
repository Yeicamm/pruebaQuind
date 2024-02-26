package com.technical.test.quind.hexagonal.infrastructure.rest.controller;

import com.technical.test.quind.hexagonal.application.usecases.ProductService;
import com.technical.test.quind.hexagonal.domain.model.constant.MessageApplication;
import com.technical.test.quind.hexagonal.domain.model.dto.EditAccountStatusDto;
import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/createAccount")
    public String createAccount(@RequestBody ProductDto productDto) {
        try {
            productService.accountCreate(productDto);
            return MessageApplication.ACCOUNTCREATED;
        } catch (Exception e) {
            return MessageApplication.ACCOUNTCANCELLED;
        }
    }

    @PatchMapping("/updateAccountState")
    public String updateAccountState(@RequestBody EditAccountStatusDto editAccountStatusDto) {
        try {
            productService.accountStateUpdate(editAccountStatusDto);
            return MessageApplication.UPDATEACCOUNTS;
        } catch (Exception e) {
            return MessageApplication.ACCOUNTNOTFOUND;
        }
    }

    @PatchMapping("/accountCanceled")
    public String accountCanceled(@RequestBody EditAccountStatusDto editAccountStatusDto) {
        try {
            productService.accountCanceled(editAccountStatusDto);
            return MessageApplication.ACCOUNTCANCELLED;
        } catch (Exception e) {
            return MessageApplication.ACCOUNTCANCELLED;
        }
    }

    @PostMapping("/consign/{accountNumber}")
    public String consign(@PathVariable String accountNumber, @RequestParam BigDecimal balance) {
        try {
            productService.consignMoney(accountNumber, balance);
            return MessageApplication.CONSIGNSUCESSFULL;
        } catch (IllegalArgumentException e) {
            return MessageApplication.ACCOUNTNOTFOUND;
        } catch (Exception e) {
            return MessageApplication.ACCOUNTCANCELLED;
        }
    }

    @PostMapping("/withdraw/{accountNumber}")
    public String withdrawMoney(@PathVariable String accountNumber, @RequestParam BigDecimal balance) {
        try {
            productService.withdrawMoney(accountNumber, balance);
            return MessageApplication.WITHDRAWSUCCESSFULL;
        } catch (IllegalArgumentException e) {
            return MessageApplication.ACCOUNTNOTFOUND;
        } catch (Exception e) {
            return MessageApplication.ACCOUNTCANCELLED;
        }
    }

    @PostMapping("/transfer")
    public String transferMoney(@RequestParam String accountOrigin, @RequestParam String accountDestination, @RequestParam BigDecimal balance) {
        try {
            productService.transferMoney(accountOrigin, accountDestination, balance);
            return MessageApplication.TRANSFERSUCCESFULL;
        } catch (IllegalArgumentException e) {
            return MessageApplication.ACCOUNTNOTFOUND;
        } catch (Exception e) {
            return MessageApplication.ACCOUNTCANCELLED;
        }
    }
}
