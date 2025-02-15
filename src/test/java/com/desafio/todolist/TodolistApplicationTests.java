package com.desafio.todolist;

import com.desafio.todolist.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodolistApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testTodoSucess() {
		var todo = new Todo("todo 1", "desc todo 1" ,false, 1);

	webTestClient.post()
			.uri("/todo")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].nome").isEqualTo(todo.getNome())
			.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
			.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
			.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testTodoBadRequest() {
		webTestClient.post()
				.uri("/todo")
				.bodyValue(new Todo("", "", false,0)).exchange().expectStatus().isBadRequest();
	}

	@Test
	void testTodoBadRequestNome() {
		webTestClient.post()
				.uri("/todo")
				.bodyValue(new Todo("", "desc", false,0)).exchange().expectStatus().isBadRequest();
	}

	@Test
	void testTodoBadRequestDesc() {
		webTestClient.post()
				.uri("/todo")
				.bodyValue(new Todo("todo 1", "", false,0)).exchange().expectStatus().isBadRequest();
	}

}
