package com.technical.test.quind.hexagonal.domain.model;

import com.technical.test.quind.hexagonal.domain.model.enums.AccountState;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.Audit;
import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Product extends Audit {
    private Long id;
    private String accountNumber;
    private AccountState accountState;
    private BigDecimal balance;
    private boolean gmfExempt;
    private ClientEntity clientEntity;
}
