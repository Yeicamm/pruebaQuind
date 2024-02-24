package com.technical.test.quind.hexagonal.domain.port;

import com.technical.test.quind.hexagonal.domain.model.dto.EditAccountStatusDto;
import com.technical.test.quind.hexagonal.domain.model.dto.request.RequestAccountClientDto;

import java.math.BigDecimal;

public interface ProductPersistencePort {

    Object accountCreate(RequestAccountClientDto requestAccountClientDto);
    Object accountStateUpdate(EditAccountStatusDto editAccountStatusDto);
    Object accountCanceled(EditAccountStatusDto editAccountStatusDto);
    Object consignMoney(String accountNumber, BigDecimal balance);
    Object withdrawMoney(String accountNumber, BigDecimal balance);
    void transferMoney(String accountOrigin, String accountDestination, BigDecimal balance);
}
