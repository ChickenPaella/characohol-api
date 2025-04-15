package com.characohol.characohol_api.service;

import com.characohol.characohol_api.domain.Recommendation;
import com.characohol.characohol_api.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public List<Recommendation> getRecommendations() {
        return recommendationRepository.findAll();
    }

    public Recommendation getByTag(String tag) {
        return recommendationRepository.findByTag(tag).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Recommendation create(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    public Recommendation update(Long id, Recommendation recommendation) {
        Recommendation target = recommendationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        target.setTag(recommendation.getTag());
        target.setCharacter(recommendation.getCharacter());
        target.setMessage(recommendation.getMessage());
        return recommendationRepository.save(target);
    }

    public void delete(Long id) {
        if (!recommendationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        recommendationRepository.deleteById(id);
    }
}
