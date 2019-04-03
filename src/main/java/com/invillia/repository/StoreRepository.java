package com.invillia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.models.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

	Optional<Store> findByNome(String nome);
	
}
