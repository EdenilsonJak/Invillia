package com.invillia.repository;

import java.util.Optional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.invillia.models.Order;
import com.invillia.models.Payment;

@NamedQueries({
	@NamedQuery(name="OrderRepository.findByPaymentId",
			query="SELECT pedido FROM Order pedido WHERE pedido.payment.id = :paymentId")
	})

public interface OrderRepository extends JpaRepository<Order, Long> {
		
	Optional<Order> findById(Long id);
	
	Order findByEndereco(String nome);
	
	//query native
	@Query(
		value="select * from order o where o.payment.id = :paymentId",
		nativeQuery = true
			)
	
	public Order paymentPorOrder(@Param("paymentId") Long id);

}
