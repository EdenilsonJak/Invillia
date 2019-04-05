package com.invillia.repository;

import java.util.Optional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.invillia.models.Payment;

@NamedQueries({
	@NamedQuery(name="PaymentRepository.findByOrderId",
			query="SELECT payment FROM Payment payment WHERE payment.order.id = :orderId")
	})

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Optional<Payment> findById(Long id);
	
	Optional<Payment> findByOrderId(@Param("orderId") Long id);
		
}
