package com.characohol.characohol_api.controller

import com.characohol.characohol_api.domain.Recommendation
import com.characohol.characohol_api.service.RecommendationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/recommendations")
class RecommendationController(private val recommendationService: RecommendationService) {

    @GetMapping("/{tag}")
    fun getByTag(@PathVariable tag: String): Mono<ResponseEntity<Recommendation>> {
        return recommendationService.getByTag(tag)
            .map { ResponseEntity.ok(it) }
            .onErrorResume { e ->
                if (e is org.springframework.web.server.ResponseStatusException) {
                    Mono.just(ResponseEntity.status(e.statusCode).build())
                } else {
                    Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build())
                }
            }
    }

    @GetMapping
    fun getAll(): Flux<Recommendation> {
        return recommendationService.getRecommendations()
    }

    @PostMapping
    fun create(@RequestBody req: Recommendation): Mono<ResponseEntity<Recommendation>> {
        return recommendationService.create(req)
            .map { ResponseEntity.status(HttpStatus.CREATED).body(it) }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody req: Recommendation): Mono<ResponseEntity<Recommendation>> {
        return recommendationService.update(id, req)
            .map { ResponseEntity.ok(it) }
            .onErrorResume { e ->
                if (e is org.springframework.web.server.ResponseStatusException) {
                    Mono.just(ResponseEntity.status(e.statusCode).build())
                } else {
                    Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build())
                }
            }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): Mono<ResponseEntity<Void>> {
        return recommendationService.delete(id)
            .then(Mono.just(ResponseEntity.noContent().build<Void>()))
            .onErrorResume { e ->
                if (e is org.springframework.web.server.ResponseStatusException) {
                    Mono.just(ResponseEntity.status(e.statusCode).build())
                } else {
                    Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build())
                }
            }
    }
}