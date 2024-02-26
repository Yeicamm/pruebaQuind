package com.technical.test.quind.hexagonal.infrastructure.adapter.repository;

import com.technical.test.quind.hexagonal.infrastructure.adapter.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findClientEntityByIdentificationNumber(String identificationNumber);
}
