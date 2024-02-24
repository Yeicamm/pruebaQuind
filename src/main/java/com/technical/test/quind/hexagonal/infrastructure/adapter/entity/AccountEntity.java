package com.technical.test.quind.hexagonal.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_type")
    private String accountType;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

}
