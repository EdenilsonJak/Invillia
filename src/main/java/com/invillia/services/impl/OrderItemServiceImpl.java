package com.invillia.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.models.OrderItem;
import com.invillia.repository.OrderItemRepository;
import com.invillia.services.OrderItemService;
import com.invillia.services.StoreService;

@Service
public class OrderItemServiceImpl implements OrderItemService{

	private static final Logger log = LoggerFactory.getLogger(StoreService.class);
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public OrderItem persistir(OrderItem orderItem) {
		log.info("Cadastrar OrderItem no banco de dados: {}", orderItem);
		return this.orderItemRepository.save(orderItem);
	}

	@Override
	public Optional<OrderItem> buscarPorId(Long id) {
		log.info("Pesquisar OrderItem no banco de dados por id: {}", id);
		return this.orderItemRepository.findById(id);
	}


}
