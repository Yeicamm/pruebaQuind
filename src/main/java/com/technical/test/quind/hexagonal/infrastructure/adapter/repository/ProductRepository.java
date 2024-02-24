package com.technical.test.quind.hexagonal.infrastructure.adapter.repository;

import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findProductEntityByAccountNumber(String accountNumber);
    Boolean existsByAccountNumber(String accountNumber);
}
