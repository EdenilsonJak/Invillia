package com.invillia.services;

import java.util.Optional;

import com.invillia.models.Payment;

public interface PaymentService {

	/**
	 * retorna um payment dado um id
	 * 
	 * @param nome
	 * @return
	 */
	Optional<Payment> buscarPorId(Long id);
	
	/**
	 *  Cadastra um payment na base de dados.
	 * @param store
	 * @return
	 */
	
	Payment persistir(Payment payment);

	
}
