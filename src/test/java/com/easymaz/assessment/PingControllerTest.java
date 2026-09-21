package com.easymaz.assessment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Requires MongoDB to be running: docker compose up -d
 */
@SpringBootTest
@AutoConfigureWebTestClient
class PingControllerTest {

    @Autowired
    WebTestClient client;

    @Test
    void pingReportsMongoUp() {
        client.get().uri("/ping")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.mongo").isEqualTo("up");
    }
}
