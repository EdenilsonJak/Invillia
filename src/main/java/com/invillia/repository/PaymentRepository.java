package com.invillia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Optional<Payment> findById(Long id);
}
