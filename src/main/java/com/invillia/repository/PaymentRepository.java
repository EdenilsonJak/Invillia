package com.invillia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.invillia.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Optional<Payment> findById(Long id);
	
	//query native
		@Query(
			value="select * from payment where order.id = :orderId",
			nativeQuery = true
				)
	Optional<Payment> findPaymentOrderId(@Param("orderId") Long id);

}
