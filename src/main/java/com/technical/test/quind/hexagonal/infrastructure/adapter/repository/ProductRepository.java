package com.technical.test.quind.hexagonal.infrastructure.adapter.repository;

import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
