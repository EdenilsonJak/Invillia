package com.invillia.repository;

import java.util.Optional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.models.Order;

@NamedQueries({
	@NamedQuery(name="OrderRepository.findByPaymentId",
			query="SELECT pedido FROM Order pedido WHERE pedido.payment.id = :paymentId")
	})

public interface OrderRepository extends JpaRepository<Order, Long> {
		
	Optional<Order> findById(Long id);
	
	Order findByEndereco(String nome);

}
