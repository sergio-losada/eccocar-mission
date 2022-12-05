package com.eccocar.mission;

import java.io.IOException;
import java.lang.String;
import java.net.Socket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = { MissionApplicationTests.class },
    properties = "spring.main.lazy-initialization=true"
)
public class MissionApplicationTests {
  @Value("${spring.data.mongodb.uri}")
  private static String uri;

  @Value("${spring.data.mongodb.database}")
  private static String database;

  @Container
  public static MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

  @DynamicPropertySource
  public static void mongoDbProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
  }

  @BeforeEach
  public void init() {
    container.start();
  }

  @Test
  public void test01_port() {
    this.checkPortAvailability(container);
  }

  private void checkPortAvailability(MongoDBContainer container) {
    Socket socket;
    try {
      socket = new Socket(container.getContainerIpAddress(), container.getFirstMappedPort());
      socket.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
