package com.characohol.characohol_api.repository

import com.characohol.characohol_api.domain.Recommendation
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface RecommendationRepository : ReactiveCrudRepository<Recommendation, Long> {
    fun findByTag(tag: String): Mono<Recommendation>
}