package com.invillia.services;

import java.util.Optional;

import com.invillia.models.OrderItem;

public interface OrderItemService {

	/**
	 * Cadastrar OrderItem no banco de dados
	 * @param orderItem
	 * @return
	 */
	OrderItem persistir(OrderItem orderItem);
	
	
	/**
	 * Buscar OrderItem por id 
	 * @param id
	 * @return
	 */
	Optional<OrderItem> buscarPorId(Long id);
}
