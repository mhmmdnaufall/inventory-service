package mhmmdnaufall.inventoryservice;

import mhmmdnaufall.inventoryservice.entity.Inventory;
import mhmmdnaufall.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class InventoryServiceApplicationTests {

	@Container
	private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private InventoryRepository inventoryRepository;

	@DynamicPropertySource
	private static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.driver-class-name", MY_SQL_CONTAINER::getDriverClassName);
		registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
		registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
		registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
	}

	@BeforeAll
	static void beforeAll() {
		MY_SQL_CONTAINER.start();
	}

	@AfterAll
	static void afterAll() {
		MY_SQL_CONTAINER.close();
	}

	@AfterEach
	void tearDown() {
		inventoryRepository.deleteAll();
	}

	@Test
	void isInStock() throws Exception {
		final var inventory = new Inventory(1L, "iphone_13", 100);
		inventoryRepository.save(inventory);

		mockMvc
				.perform(get("/api/inventory/iphone_13"))
				.andExpect(status().isOk())
				.andDo(result -> assertTrue(Boolean.parseBoolean(result.getResponse().getContentAsString())));
	}

	@Test
	void isNotInStock() throws Exception {
		mockMvc
				.perform(get("/api/inventory/nothing"))
				.andExpect(status().isOk())
				.andDo(result -> assertFalse(Boolean.parseBoolean(result.getResponse().getContentAsString())));
	}
}
