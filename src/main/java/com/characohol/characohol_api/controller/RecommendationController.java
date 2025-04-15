package com.characohol.characohol_api.controller;

import com.characohol.characohol_api.domain.Recommendation;
import com.characohol.characohol_api.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/{tag}")
    public Mono<ResponseEntity<Recommendation>> getByTag(@PathVariable String tag) {
        return Mono.fromCallable(() -> recommendationService.getByTag(tag))
                .map(ResponseEntity::ok)
                .onErrorResume(
                        ResponseStatusException.class,
                        e -> Mono.just(ResponseEntity.status(e.getStatusCode()).build())
                )
                .subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping
    public Flux<Recommendation> getAll() {
        return Flux.defer(() -> Flux.fromIterable(recommendationService.getRecommendations()))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping
    public Mono<ResponseEntity<Recommendation>> create(@RequestBody Recommendation req) {
        return Mono.fromCallable(() -> recommendationService.create(req))
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Recommendation>> update(@PathVariable Long id, @RequestBody Recommendation req) {
        return Mono.fromCallable(() -> recommendationService.update(id, req))
                .map(ResponseEntity::ok)
                .onErrorResume(ResponseStatusException.class,
                        e -> Mono.just(ResponseEntity.status(e.getStatusCode()).build()))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<ResponseEntity<Void>> delete(@PathVariable Long id) {
        return Mono.fromRunnable(() -> recommendationService.delete(id))
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .onErrorResume(ResponseStatusException.class,
                        e -> Mono.just(ResponseEntity.status(e.getStatusCode()).build()))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
