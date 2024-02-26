package com.technical.test.quind.hexagonal.domain.model.dto;

import com.technical.test.quind.hexagonal.domain.model.enums.AccountState;
import com.technical.test.quind.hexagonal.domain.model.enums.AccountType;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.Audit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductDto extends Audit {
    private String accountType;
    private String accountNumber;
    private AccountState accountState;
    private BigDecimal balance;
    private boolean gmfExempt;
    private Long clientId;
    private AccountType accountTypes;
}
