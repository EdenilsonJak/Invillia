package com.invillia.services;

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

import com.invillia.models.Store;
import com.invillia.repository.StoreRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class StoreServiceTest {
	
	@MockBean
	private StoreRepository storeRepository;
	
	@Autowired
	private StoreService storeService;
	
	private static final String nome = "E.J.SISTEMAS";
	
	@Before
	public void setUp() throws Exception{
		//BDDMockito.given(this.storeRepository.findByNome(Mockito.anyString())).willReturn(new Store());
		BDDMockito.given(this.storeRepository.save(Mockito.any(Store.class))).willReturn(new Store());
	}
	
	/*
	 * @Test public void testBuscarStoreNome() { Optional<Store> store =
	 * this.storeService.buscarPorNome(nome); assertTrue(store.isPresent()); }
	 */
	
	@Test
	public void testPersistirStore() {
		Store store = this.storeService.persistir(new Store());
		
		assertNotNull(store);
	}

}
