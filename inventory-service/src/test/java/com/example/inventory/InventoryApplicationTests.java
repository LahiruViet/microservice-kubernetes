package com.example.inventory;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryApplicationTests {

	@ServiceConnection
	private final static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	static {
		mySQLContainer.start();
	}

	@BeforeEach
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	public void shouldReadInventory() {

		var response = RestAssured.given()
				.when()
				.get("/api/v1/inventory?skuCode=iphone_15&quantity=1")
				.then()
				.log().all()
				.statusCode(200)
				.extract().response().as(Boolean.class);

		assertTrue(response);

		var negativeResponse = RestAssured.given()
				.when()
				.get("/api/v1/inventory?skuCode=iphone_15&quantity=1000")
				.then()
				.log().all()
				.statusCode(200)
				.extract().response().as(Boolean.class);

		assertFalse(negativeResponse);
	}
}
