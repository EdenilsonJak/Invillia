package com.invillia.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.models.Order;
import com.invillia.repository.OrderRepository;
import com.invillia.services.OrderService;
import com.invillia.services.StoreService;

@Service
public class OrderServiceImpl implements OrderService{

	private static final Logger log = LoggerFactory.getLogger(StoreService.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order persistir(Order order) {
		log.info("Persistindo um Order: {}", order);
		return this.orderRepository.save(order);
	}

	@Override
	public Order buscarPorEndereco(String endereco) {
		log.info("buscando um pedido por endereco: {}", endereco);
		return this.orderRepository.findByEndereco(endereco);
	}

	@Override
	public Optional<Order> buscarPorId(Long id) {
		log.info("buscando pedido por id: {}", id);
		return this.orderRepository.findById(id);
	}

	@Override
	public Order buscarPorIdPaymente(Long id) {
		return this.orderRepository.paymentPorOrder(id);
	}
}
