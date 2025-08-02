package com.example.demo.repositories;

import com.example.demo.entities.SosForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SosFormRepository extends JpaRepository<SosForm, UUID> {
    Optional<SosForm> findByUserId(UUID userId);
}
