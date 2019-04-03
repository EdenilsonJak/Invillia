package com.invillia.services;

import java.util.Optional;

import com.invillia.models.Store;

public interface StoreService {

	/**
	 * retorna um store dado um nome
	 * 
	 * @param nome
	 * @return
	 */
	Optional<Store> buscarPorNome(String nome);
	
	/**
	 *  Cadastra um store na base de dados.
	 * @param store
	 * @return
	 */
	
	Store persistir(Store store);
	
	
}
