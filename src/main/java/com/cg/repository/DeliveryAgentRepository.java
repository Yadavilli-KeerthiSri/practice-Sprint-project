package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.DeliveryAgent;

import java.util.List;
import java.util.Optional;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long> {
    List<DeliveryAgent> findByAvailabilityTrue();
    boolean existsByContact(String contact);
    Optional<DeliveryAgent> findByContact(String contact);
}
