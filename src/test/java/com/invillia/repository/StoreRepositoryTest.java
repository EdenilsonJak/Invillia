package com.invillia.repository;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.invillia.models.Store;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class StoreRepositoryTest {

	@Autowired
	private StoreRepository storeRepository;
	
	private static final String STORE = "E.J.SISTEMAS";
	
	@Before
	public void setUp() throws Exception {
		Store store = new Store();
		store.setNome("E.J.SISTEMAS");
		store.setEndereco("av. moaçara, 885");
		this.storeRepository.save(store);
	}
	
	@After
	public final void tearDown() {
		this.storeRepository.deleteAll();
	}
	
	@Test
	public void testBuscarPorNome()	{
		Optional<Store> store = this.storeRepository.findByNome(STORE);
		
		if(store.isPresent()) {
			assertEquals(STORE, store.get().getNome());
		}
		
	}
}
