package com.technical.test.quind.hexagonal.domain.model;

import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private String accountType;
    private ProductEntity productEntity;
}
