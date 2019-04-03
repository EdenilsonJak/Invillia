package com.invillia.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.invillia.models.Order;
import com.invillia.models.OrderItem;
import com.invillia.models.Payment;
import com.invillia.status.OrderStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderRepositoryTest {

	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	private Long idOrder;
	
	@Before
	public void setUp() throws Exception {

		Payment payment = this.paymentRepository.save(obterPaymente());
		
		Order pedido = this.orderRepository.save(obterPedido(payment));
		
		OrderItem orderItem1 = this.orderItemRepository.save(obterOrderItens(pedido));
		OrderItem orderItem2 = this.orderItemRepository.save(obterOrderItens(pedido));
		
		payment.setOrder(pedido);
		this.paymentRepository.save(payment);
		
		idOrder = pedido.getId();
		//this.paymentRepository.save(payment);
	
		
	}

	@After
	public void tearDown() throws Exception {
		/*
		 * this.paymentRepository.deleteAll(); this.orderRepository.deleteAll();
		 */
	}
	
	@Test
	public void testBuscarOrder() {
		Optional<Order> order = this.orderRepository.findById(idOrder);
		
		if(order.isPresent()) {
			assertEquals(idOrder, order.get().getId());
		}
	}
	
	private OrderItem obterOrderItens(Order pedido) {
		OrderItem item = new OrderItem();
		item.setDescription("Arroz");
		item.setOrder(pedido);
		item.setQtd(new Double(2));
		item.setUnitValue(new Double(1.20));
		
		return item;
	}

	private Order obterPedido(Payment pgto) {
		Order pedido = new Order();
		pedido.setDataOrder(new Date());
		pedido.setEndereco("av. marechal");
		pedido.setOrderStatus(OrderStatus.PENDING_PAYMENT);
		pedido.setPayment(pgto);
		
		return pedido;
	}

	private Payment obterPaymente() {
		Payment payment = new Payment();
		
		payment.setMomentpgto(new Date());
		payment.setNumCredito(new Long(1234456));
		payment.setOrderStatus(OrderStatus.PENDING_PAYMENT);
		return payment;
	}
	
}
