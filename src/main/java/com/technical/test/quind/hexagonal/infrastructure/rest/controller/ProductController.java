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
    public Object createAccount(@RequestBody ProductDto productDto) {
        return productService.accountCreate(productDto);
    }

    @PatchMapping("/updateAccountState")
    public Object updateAccountState(@RequestBody EditAccountStatusDto editAccountStatusDto) {
        return productService.accountStateUpdate(editAccountStatusDto);
    }

    @PatchMapping("/accountCanceled")
    public Object accountCanceled(@RequestBody EditAccountStatusDto editAccountStatusDto) {
        return productService.accountCanceled(editAccountStatusDto);
    }

    @PostMapping("/consign/{accountNumber}")
    public Object consign(@PathVariable String accountNumber, @RequestParam BigDecimal balance) {
        return productService.consignMoney(accountNumber, balance);
    }

    @PostMapping("/withdraw/{accountNumber}")
    public Object withdrawMoney(@PathVariable String accountNumber, @RequestParam BigDecimal balance) {
        return productService.withdrawMoney(accountNumber, balance);
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
