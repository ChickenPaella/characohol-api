package com.characohol.characohol_api.service

import com.characohol.characohol_api.domain.Recommendation
import com.characohol.characohol_api.repository.RecommendationRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class RecommendationService(private val recommendationRepository: RecommendationRepository) {

    fun getRecommendations(): Flux<Recommendation> {
        return recommendationRepository.findAll()
    }

    fun getByTag(tag: String): Mono<Recommendation> {
        return recommendationRepository.findByTag(tag)
            .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND)))
    }

    fun create(recommendation: Recommendation): Mono<Recommendation> {
        return recommendationRepository.save(recommendation)
    }

    fun update(id: Long, recommendation: Recommendation): Mono<Recommendation> {
        return recommendationRepository.findById(id)
            .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND)))
            .flatMap { target ->
                recommendationRepository.save(
                    Recommendation(
                        id = target.id,
                        tag = recommendation.tag,
                        character = recommendation.character,
                        message = recommendation.message
                    )
                )
            }
    }

    fun delete(id: Long): Mono<Void> {
        return recommendationRepository.existsById(id)
            .flatMap { exists ->
                if (exists) {
                    recommendationRepository.deleteById(id)
                } else {
                    Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND))
                }
            }
    }
}