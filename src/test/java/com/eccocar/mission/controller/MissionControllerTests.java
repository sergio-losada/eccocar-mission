package com.eccocar.mission.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

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

import com.eccocar.mission.dao.MissionDAO;
import com.eccocar.mission.dto.Mission;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
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
      String starship = "https://swapi.dev/api/starships/5/";
      ArrayList<String> people = new ArrayList<>();
      people.add("https://swapi.dev/api/people/1/");
      ArrayList<String> planets = new ArrayList<>();
      planets.add("https://swapi.dev/api/planets/1/");
      MissionDAO mission = new MissionDAO(UUID.randomUUID(),"missionTEST", new Date(), starship, people, 50, planets, 100.0);
      // POST - WebTestClient
      this.webTestClient
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
            assertEquals(response.getResponseBody().getName(), "missionTEST", "Mission names must be equals");
            assertEquals(response.getResponseBody().getStarship().getUrl(), "https://swapi.dev/api/starships/5/", "Starships must be equals");
            assertEquals(response.getResponseBody().getPeople().get(0).getUrl(), "https://swapi.dev/api/people/1/", "People must be equals");
            assertEquals(response.getResponseBody().getPlanets().get(0).getUrl(), "https://swapi.dev/api/planets/1/", "Planets must be equals");
            assertEquals(response.getResponseBody().getCrew(), 50, "Number of crew members must be equals");
            assertEquals(response.getResponseBody().getReward(), 100.0, "Mission rewards must be equals");
         }
      );
   }

   /**
    * GET endpoint controller test
    */
   @Test
   @Order(2)
   public void test02_get() {
      // GET - WebTestClient
      this.webTestClient
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
         }
      );
   }

   @Test
	@Order(3)
	public void test03_delete() {
		// DELETE - WebTestClient
		this
		.webTestClient
		.delete()
		.uri(ABSOLUTE_URI + "/missionTEST")
		.exchange()
		.expectStatus().isNoContent()
		.expectBody(Void.class)
		.consumeWith(response -> {
			// Testing response
			assertNotNull(response, "Response entity must be not null");
			assertEquals(response.getStatus(), HttpStatus.NO_CONTENT, "HTTP status code must be 204 NO CONTENT");
			// Testing response body
			assertNull(response.getResponseBody(), "Response body must be null");	
		});
	}

}
