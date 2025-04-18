package com.characohol.characohol_api.tool

import com.characohol.characohol_api.domain.Recommendation
import com.characohol.characohol_api.repository.RecommendationRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(private val recommendationRepository: RecommendationRepository) : CommandLineRunner {

    override fun run(vararg args: String) {
        recommendationRepository.save(Recommendation(
            id = null,
            tag = "조용한",
            character = "신지",
            message = "혼자 마시는 술을 좋아하는 타입"
        )).flatMap {
            recommendationRepository.save(Recommendation(
                id = null,
                tag = "사교적",
                character = "나루토",
                message = "친구들과 어울리는 걸 좋아함"
            ))
        }.subscribe()
    }
}