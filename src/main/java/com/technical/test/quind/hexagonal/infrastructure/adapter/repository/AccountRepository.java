package com.technical.test.quind.hexagonal.infrastructure.adapter.repository;

import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
