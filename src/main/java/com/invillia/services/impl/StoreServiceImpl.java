package com.invillia.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.models.Store;
import com.invillia.repository.StoreRepository;
import com.invillia.services.StoreService;

@Service
public class StoreServiceImpl implements StoreService{

	private static final Logger log = LoggerFactory.getLogger(StoreService.class);
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Override
	public Optional<Store> buscarPorNome(String nome) {
		log.info("Buscando uma empresa por Nome{}", nome);
		return this.storeRepository.findByNome(nome);
	}

	@Override
	public Store persistir(Store store) {
		log.info("Persistindo Store: {}", store);
		return this.storeRepository.save(store);
	}

}
