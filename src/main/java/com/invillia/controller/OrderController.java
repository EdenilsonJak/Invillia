package com.invillia.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.dtos.CadastroOrderDto;
import com.invillia.models.Order;
import com.invillia.models.Payment;
import com.invillia.response.Response;
import com.invillia.services.OrderService;
import com.invillia.services.PaymentService;
import com.invillia.status.OrderStatus;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PaymentService paymentService;
	
	public OrderController() {
	}
	
	
	/**
	 * Cadastro de Pedido na base de dados
	 * @param cadastroOrderDto
	 * @param result
	 * @return
	 * @throws ParseException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroOrderDto>> adicionar(@Valid @RequestBody CadastroOrderDto cadastroOrderDto,
			BindingResult result) throws ParseException {
		log.info("Adicionando Order: {}", cadastroOrderDto.toString());
		Response<CadastroOrderDto> response = new Response<CadastroOrderDto>();
		Order order = this.converterDtoParaOrder(cadastroOrderDto, result);
		
		if(result.hasErrors()){
			log.error("Erro validando store: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		order = this.orderService.persistir(order);
		response.setData(this.converterOrderDto(order));
		return ResponseEntity.ok(response);
	}

	/**
	 * Atualiza um pedido por id
	 * @param id
	 * @param cadastroOrderDto
	 * @param result
	 * @return
	 * @throws ParseException
	 */
	@PutMapping(value="/{id}")
	public ResponseEntity<Response<CadastroOrderDto>> atualizar(@PathVariable("id") Long id,
		@Valid @RequestBody CadastroOrderDto cadastroOrderDto, BindingResult result) throws ParseException {
			log.info("Atualizando Order :{}", cadastroOrderDto.toString());
			Response<CadastroOrderDto> response = new Response<CadastroOrderDto>();
			//validaPayment();
			Order order = this.converterDtoParaOrder(cadastroOrderDto, result);
			
			if(result.hasErrors()){
				log.error("Erro validando store: {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			order = this.orderService.persistir(order);
			response.setData(this.converterOrderDto(order));
			return ResponseEntity.ok(response);
			
		}
	
	/**
	 * Buscar Order por id
	 * @param id
	 * @return
	 */
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<CadastroOrderDto>> listarPorId(@PathVariable("id") Long id){
		log.info("Buscando Order por ID: {}", id);
		Response<CadastroOrderDto> response = new Response<CadastroOrderDto>();
		Optional<Order> order = this.orderService.buscarPorId(id);
		
		//checkaPayment(order);
		
		if(!order.isPresent()){
			log.info("Pedido não encontrado para o ID: {}", id);
			response.getErros().add("Pedido não encontrado para o Id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterOrderDto(order.get()));
		return ResponseEntity.ok(response);
	}
	
	
	private Long confirmarIdPayment(Long id) {
		Optional<Payment> payment = this.paymentService.buscarPorOrderId(id);
		if(payment.isPresent()) {
			return payment.get().getId();
		}
		return null;
	}


	private void checkaPayment(Optional<Order> order) {
		
	}


	private CadastroOrderDto converterOrderDto(Order order) {
		CadastroOrderDto storeDto = new CadastroOrderDto();
		storeDto.setId(Optional.of(order.getId()));
		storeDto.setDataOrder(this.dateFormat.format(order.getDataOrder()));
		storeDto.setOrderStatus(order.getOrderstatus().toString());
		storeDto.setEndereco(order.getEndereco());
		storeDto.setPaymentId(confirmarIdPayment(order.getId()));
		return storeDto;
		
		
	}

	private Order converterDtoParaOrder(@Valid CadastroOrderDto cadastroOrderDto, BindingResult result) {
		Order order = new Order();
		order.setEndereco(cadastroOrderDto.getEndereco());
		order.setOrderstatus(OrderStatus.valueOf(cadastroOrderDto.getOrderStatus()));
		order.setDataOrder(new Date());
		
		return order;
	}
	
}
