package com.invillia.controller;

import java.text.ParseException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.dtos.CadastroOrderItemDto;
import com.invillia.models.Order;
import com.invillia.models.OrderItem;
import com.invillia.repository.OrderItemRepository;
import com.invillia.repository.OrderRepository;
import com.invillia.response.Response;
import com.invillia.services.OrderService;

@RestController
@RequestMapping("/api/orderitens")
@CrossOrigin("*")
public class OrderItemController {

	private static final Logger log = LoggerFactory.getLogger(OrderItemController.class);
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderService orderService;
	
	public OrderItemController() {
	}
	
	@PostMapping
	public ResponseEntity<Response<CadastroOrderItemDto>> adicionar(@Valid @RequestBody CadastroOrderItemDto cadastroOrderItemDto,
			BindingResult result) throws ParseException{
		log.info("Adicionando OrderItem: {}", cadastroOrderItemDto.toString());
		Response<CadastroOrderItemDto> response = new Response<CadastroOrderItemDto>();
		validOrder(cadastroOrderItemDto, result);
		
	}

	private void validOrder(@Valid CadastroOrderItemDto cadastroOrderItemDto, BindingResult result) {
		Optional<Order> order= this.orderService.buscarPorId(cadastroOrderItemDto.getId().get());
		if(!order.isPresent()) {
			result.addError(new ObjectError("Order", "Pedido não encontrado!"));
			return;
		}
		
	}
	
}
