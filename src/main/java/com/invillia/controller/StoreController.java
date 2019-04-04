package com.invillia.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.dtos.CadastroStoreDto;
import com.invillia.models.Store;
import com.invillia.response.Response;
import com.invillia.services.StoreService;

@RestController
@RequestMapping("/api/cadastrostore")
@CrossOrigin(origins="*")
public class StoreController {

	private static final Logger log = LoggerFactory.getLogger(StoreController.class);
	
	@Autowired
	private StoreService storeService;
	
	public StoreController() {
	}
	
	@PostMapping
	public ResponseEntity<Response<CadastroStoreDto>> cadastrar(@Valid @RequestBody CadastroStoreDto cadastroStoreDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Store: {}", cadastroStoreDto.toString());
		Response<CadastroStoreDto> response = new Response<CadastroStoreDto>();
		
		validarDadosExistente(cadastroStoreDto, result);
		Store store = this.converterDtoParaStore(cadastroStoreDto);
		
		if(result.hasErrors()) {
			log.error("Error validando dados de cadastro Store: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.storeService.persistir(store);
		response.setData(this.converterCadastroStoreDto(store));
		return ResponseEntity.ok(response);
		
	}
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Response<CadastroStoreDto>> buscarPorId(@PathVariable("id") Long id){
		log.info("Buscando Store por id: {}",id);
		Response<CadastroStoreDto> response = new Response<CadastroStoreDto>();
		Optional<Store> store = storeService.buscarPorId(id);
		
		if(!store.isPresent()) {
			log.info("Store não encontrando para id :{}", id);
			response.getErros().add("Store não encontrado para " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterCadastroStoreDto(store.get()));
		return ResponseEntity.ok(response);
	}
	
	
	
	/**
	 * popular cadastroStoreDto com os dados de Store
	 * @param store
	 * @return
	 */
	private CadastroStoreDto converterCadastroStoreDto(Store store) {
		CadastroStoreDto cadastroStoreDto = new CadastroStoreDto();
		cadastroStoreDto.setId(store.getId());
		cadastroStoreDto.setNome(store.getNome());
		cadastroStoreDto.setEndereco(store.getEndereco());
		return cadastroStoreDto;
	}

	/**
	 * converter StoreDTO para Store
	 * @param cadastroStoreDto
	 * @return Store
	 */

	private Store converterDtoParaStore(@Valid CadastroStoreDto cadastroStoreDto) {
		Store store = new Store();
		store.setNome(cadastroStoreDto.getNome());
		store.setEndereco(cadastroStoreDto.getEndereco());
		return store;
	}

	/**
	 * Verifica se Store já existe
	 * @param cadastroStoreDto
	 * @param result
	 */
	private void validarDadosExistente(@Valid CadastroStoreDto cadastroStoreDto, BindingResult result) {
		this.storeService.buscarPorNome(cadastroStoreDto.getNome())
			.ifPresent(store -> result.addError(new ObjectError("Loja ", "Já existente")));
		
	}
	
}
