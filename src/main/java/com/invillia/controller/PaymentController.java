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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.dtos.CadastroPaymentDto;
import com.invillia.models.Order;
import com.invillia.models.Payment;
import com.invillia.response.Response;
import com.invillia.services.OrderService;
import com.invillia.services.PaymentService;
import com.invillia.status.OrderStatus;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins="*")
public class PaymentController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private OrderService orderService;
	
	public PaymentController() {
	}
	
	@PostMapping
	public ResponseEntity<Response<CadastroPaymentDto>> adicionar(@Valid @RequestBody CadastroPaymentDto cadastroPaymentDto,
			BindingResult result) throws ParseException {
		log.info("Adicionando Order: {}", cadastroPaymentDto.toString());
		Response<CadastroPaymentDto> response = new Response<CadastroPaymentDto>();
		validaOrder(cadastroPaymentDto, result);;
		Payment payment = this.converterDtoParaPayment(cadastroPaymentDto, result);
		
		if(result.hasErrors()){
			log.error("Erro validando store: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		payment = this.paymentService.persistir(payment);
		response.setData(this.converterStoreDto(payment));
		return ResponseEntity.ok(response);
	}

	private void validaOrder(@Valid CadastroPaymentDto cadastroPaymentDto, BindingResult result) {
		if(cadastroPaymentDto.getOrderId() == null) {
			result.addError(new ObjectError("Pedido", "Pedido não informado!"));
			return;
		}
		
		log.info("Válidando pedido id {}", cadastroPaymentDto.getOrderId());
		Optional<Order> order = this.orderService.buscarPorId(cadastroPaymentDto.getOrderId());
		if(!order.isPresent()){
			result.addError(new ObjectError("Pedido", "Pedido não encontrado. ID inexistente."));
		}
		
		
	}

	private CadastroPaymentDto converterStoreDto(Payment payment) {
		CadastroPaymentDto paymentDto = new CadastroPaymentDto();
		paymentDto.setId(Optional.of(payment.getId()));
		paymentDto.setNumCredito(payment.getNumCredito().toString());
		paymentDto.setPaymentstatus(payment.getPaymentstatus().toString());
		paymentDto.setOrderId(payment.getOrder().getId());
		
		return paymentDto;
	}

	private Payment converterDtoParaPayment(@Valid CadastroPaymentDto cadastroPaymentDto, BindingResult result) {
		Payment payment = new Payment();
		
		payment.setMomentpgto(new Date());
		payment.setNumCredito(new Long(cadastroPaymentDto.getNumCredito()));
		payment.setPaymentstatus(OrderStatus.valueOf(cadastroPaymentDto.getPaymentstatus()));
		payment.setOrder(new Order());
		payment.getOrder().setId(cadastroPaymentDto.getOrderId());
		return payment;
	}
	
	
}
