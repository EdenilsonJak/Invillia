package com.invillia.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.invillia.models.Order;
import com.invillia.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTest {
	
	@MockBean
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService orderService;
	
	private static final String nome = "E.J.SISTEMAS";
	
	@Before
	public void setUp() throws Exception{
		//BDDMockito.given(this.storeRepository.findByNome(Mockito.anyString())).willReturn(new Store());
		BDDMockito.given(this.orderRepository.save(Mockito.any(Order.class))).willReturn(new Order());
		BDDMockito.given(this.orderRepository.findByEndereco(Mockito.anyString())).willReturn(new Order());
		//BDDMockito.given(this.orderRepository.findById(Mockito.any())).toString();
	}
	
	/*
	 * @Test public void testBuscarStoreNome() { Optional<Store> store =
	 * this.storeService.buscarPorNome(nome); assertTrue(store.isPresent()); }
	 */
	
	@Test
	public void testPersistirOrder() {
		Order order = this.orderService.persistir(new Order());
		
		assertNotNull(order);
	}
	
	@Test
	public void testBuscarPorEndereco() {
		Order order = this.orderService.buscarPorEndereco("E.J.SISTEMAS");
		
		assertEquals("E.J.SISTEMAS", order.getEndereco());
	}

	@Test
	public void testBuscarPorId() {
		Optional<Order> order = this.orderService.buscarPorId(1L);
		
		assertTrue(order.isPresent());
	}
	
}
