package com.technical.test.quind.hexagonal.infrastructure.adapter.entity;

import com.technical.test.quind.hexagonal.domain.model.enums.AccountState;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table (name = "product")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_state")
    private AccountState accountState;
    @Column(name = "account_balance")
    private BigDecimal balance;
    @Column(name = "GMF_exempt")
    private boolean gmfExempt;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity clientEntity;

}
