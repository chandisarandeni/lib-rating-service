package com.sarasavi.lib_rating_service.repository;

import com.sarasavi.lib_rating_service.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingsRepository extends JpaRepository<Ratings, Integer> {

}
