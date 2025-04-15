package com.characohol.characohol_api.repository;

import com.characohol.characohol_api.domain.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    Optional<Recommendation> findByTag(String tag);
}
