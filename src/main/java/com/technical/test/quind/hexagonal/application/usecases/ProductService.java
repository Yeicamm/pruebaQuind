package com.technical.test.quind.hexagonal.application.usecases;

import com.technical.test.quind.hexagonal.domain.model.dto.EditAccountStatusDto;
import com.technical.test.quind.hexagonal.domain.model.dto.ProductDto;

import java.math.BigDecimal;

public interface ProductService {
    Object accountCreate(ProductDto productDto);
    Object accountStateUpdate(EditAccountStatusDto editAccountStatusDto);
    Object accountCanceled(EditAccountStatusDto editAccountStatusDto);
    Object consignMoney(String accountNumber, BigDecimal balance);
    Object withdrawMoney(String accountNumber, BigDecimal balance);
    void transferMoney(String accountOrigin, String accountDestination, BigDecimal balance);
}
