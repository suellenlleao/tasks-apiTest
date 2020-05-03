package br.sleao.tasks.api_test;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}
	
	@Test
	public void deveRetornarTarefas() {
		RestAssured.given()
		//AÇÃO
		.when()
			.get("/todo")
		.then()
		.log().all()
			.statusCode(200)
			
			;
	}
	
	/*@Test 
	public void deveRetornarTarefasComSucesso() {
		RestAssured.given()
			.body("{\"task\": \"Incluir compras\",	\"due-Date\": \"2020-09-17\"}")
			.contentType(ContentType.JSON)
		//AÇÃO
		.when()
			.post("/todo")
		.then()
			.statusCode(201)
			;
	}*/
	
	@Test
	public void NaodeveAdicionarDataInvalidaNaTarefa() {
		RestAssured.given()
			.body("{\"task\": \"Incluir compras\",	\"due-Date\": \"2010-12-30\"}")
			.contentType(ContentType.JSON)
		//AÇÃO
		.when()
			.post("/todo")
		.then()
			.statusCode(400)
			.body("message", CoreMatchers.is("Fill the due date"))
			;
	}
}

	

