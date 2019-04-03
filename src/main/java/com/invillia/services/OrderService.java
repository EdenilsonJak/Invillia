package com.invillia.services;

import java.util.Optional;

import com.invillia.models.Order;

public interface OrderService {

	/**
	 * Cadastrar um pedido no banco de dados
	 * @param order
	 * @return
	 */
	Order persistir(Order order);
	
	/**
	 * Buscar dado um nome de endereco
	 * @param endereco
	 * @return
	 */
	Order buscarPorEndereco(String endereco);
	
	/**
	 * Buscar Order dado um Id
	 * @param id
	 * @return
	 */
	Optional<Order> buscarPorId(Long id);
}
