package com.technical.test.quind.hexagonal.domain.model.dto;

import com.technical.test.quind.hexagonal.domain.model.enums.AccountState;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    private String accountType;
    private String accountNumber;
    private AccountState accountState;
    private BigDecimal balance;
    private boolean gmfExempt;
    private Long clientId;
}
