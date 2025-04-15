package com.characohol.characohol_api.tool;

import com.characohol.characohol_api.domain.Recommendation;
import com.characohol.characohol_api.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RecommendationRepository recommendationRepository;

    @Override
    public void run(String... args) throws Exception {
        recommendationRepository.save(new Recommendation(null, "조용한", "신지", "혼자 마시는 술을 좋아하는 타입"));
        recommendationRepository.save(new Recommendation(null, "사교적", "나루토", "친구들과 어울리는 걸 좋아함"));
    }
}
