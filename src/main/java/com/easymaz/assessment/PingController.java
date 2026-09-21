package com.easymaz.assessment;

import java.util.Map;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Setup check only. Confirms the app starts and can reach MongoDB.
 * You can keep, change or delete this.
 */
@RestController
public class PingController {

    private final ReactiveMongoTemplate mongo;

    public PingController(ReactiveMongoTemplate mongo) {
        this.mongo = mongo;
    }

    @GetMapping("/ping")
    public Mono<ResponseEntity<Map<String, String>>> ping() {
        return mongo.executeCommand("{ ping: 1 }")
                .map(result -> ResponseEntity.ok(Map.of("app", "up", "mongo", "up")))
                .onErrorResume(e -> Mono.just(ResponseEntity
                        .status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(Map.of("app", "up", "mongo", "down"))));
    }
}
