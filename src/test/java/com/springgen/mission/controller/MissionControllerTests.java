package com.springgen.mission.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.springgen.mission.dto.Mission;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SuppressWarnings("null")
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class MissionControllerTests {
  private static String serverPort = "8080";

  private static final String BASE_URL = "http://localhost:" + serverPort;

  private static final String ABSOLUTE_URI = BASE_URL + "/mission";

  private WebTestClient webTestClient;

  @BeforeEach
  public void init() {
    webTestClient = WebTestClient.bindToServer().baseUrl(BASE_URL).build();
  }

  /**
   * POST endpoint controller test
   */
  @Test
  @Order(1)
  public void test01_post() {
    // Initialization: Mission object to insert
    Mission mission = new Mission(1, "a", new Date(), "a", new ArrayList<Object>(), 1, new ArrayList<Object>());
    // POST - WebTestClient
    this
    .webTestClient
    .post()
    .uri(ABSOLUTE_URI)
    .bodyValue(mission)
    .exchange()
    .expectStatus().isCreated()
    .expectBody(Mission.class)
    .consumeWith(response -> {
    // Testing response
       assertNotNull(response, "Response must not be null");
       assertEquals(response.getStatus(), HttpStatus.CREATED, "HTTP status code must be 201 CREATED");
    // Testing response body
       assertNotNull(response.getResponseBody(), "Response body must not be null");
       assertTrue(response.getResponseBody() instanceof Mission, "Response body must be a Mission object");
    });
  }

  /**
   * PUT endpoint controller test
   */
  @Test
  @Order(2)
  public void test02_put() {
    // Initialization: Mission object to update
    Mission mission = new Mission(1, "a", new Date(), "a", new ArrayList<Object>(), 1, new ArrayList<Object>());
    // PUT - WebTestClient
    this
    .webTestClient
    .put()
    .uri(ABSOLUTE_URI + "/1")
    .bodyValue(mission)
    .exchange()
    .expectStatus().isOk()
    .expectBody(Mission.class)
    .consumeWith(response -> {
    // Testing response
       assertNotNull(response, "Response must not be null");
       assertEquals(response.getStatus(), HttpStatus.OK, "HTTP status code must be 200 OK");
    // Testing response body
       assertNotNull(response.getResponseBody(), "Response body must not be null");
       assertTrue(response.getResponseBody() instanceof Mission, "Response body must be a Mission object");
    });
  }

  /**
   * GET endpoint controller test
   */
  @Test
  @Order(3)
  public void test03_get() {
    // GET - WebTestClient
    this
    .webTestClient
    .get()
    .uri(ABSOLUTE_URI)
    .exchange()
    .expectStatus().isOk()
    .expectBodyList(Mission.class)
    .consumeWith(response -> {
    // Testing response
       assertNotNull(response, "Response must not be null");
       assertEquals(response.getStatus(), HttpStatus.OK, "HTTP status code must be 200 OK");
    // Testing response body
       assertNotNull(response.getResponseBody(), "Response body must not be null");
       assertTrue(response.getResponseBody().get(0) instanceof Mission, "Response body must be a Mission object");
    });
  }

  /**
   * GET BY KEY endpoint controller test
   */
  @Test
  @Order(4)
  public void test04_getByKey() {
    // GETBYKEY - WebTestClient
    this
    .webTestClient
    .get()
    .uri(ABSOLUTE_URI + "/1")
    .exchange()
    .expectStatus().isOk()
    .expectBody(Mission.class)
    .consumeWith(response -> {
    // Testing response
       assertNotNull(response, "Response must not be null");
       assertEquals(response.getStatus(), HttpStatus.OK, "HTTP status code must be 200 OK");
    // Testing response body
       assertNotNull(response.getResponseBody(), "Response body must not be null");
       assertTrue(response.getResponseBody() instanceof Mission, "Response body must be a Mission object");
    });
  }

  /**
   * DELETE endpoint controller test
   */
  @Test
  @Order(5)
  public void test05_delete() {
    // DELETE - WebTestClient
    this
    .webTestClient
    .delete()
    .uri(ABSOLUTE_URI + "/1")
    .exchange()
    .expectStatus().isNoContent()
    .expectBody(Void.class)
    .consumeWith(response -> {
    // Testing response
       assertNotNull(response, "Response must not be null");
       assertEquals(response.getStatus(), HttpStatus.NO_CONTENT, "HTTP status code must be 204 NO CONTENT");
    // Testing response body
       assertNull(response.getResponseBody(), "Response body must be null");
    });
  }
}
