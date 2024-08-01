package com.home;

import com.home.entity.Home;
import com.home.repository.HomeRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HomeApplicationTests {

@Autowired
private HomeRepository homeRepository;

	@Test
	@Order(1)
	public void testCreate(){
		Home home = new Home();
		home.setId(10L);
		home.setName("olive");
		home.setLocation("goa");
		home.setPinCode("874512");
		homeRepository.save(home);
		assertNotNull(homeRepository.findById(10L));
	}

	@Test
	@Order(2)
	public void testReadAll () {
		List<Home> list = homeRepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void testRead () {
		Home product = homeRepository.findById(1L).get();
		assertEquals("galaxy appartment", product.getName());
	}

	@Test
	@Order(4)
	public void testUpdate () {
		Home h = homeRepository.findById(1L).get();
		h.setPinCode("693251");
		homeRepository.save(h);
		assertNotEquals("693256", homeRepository.findById(1L).get().getPinCode());
	}

	@Test
	@Order(5)
	public void testDelete () {
		homeRepository.deleteById(5L);
		assertThat(homeRepository.existsById(5L)).isFalse();
	}
}
