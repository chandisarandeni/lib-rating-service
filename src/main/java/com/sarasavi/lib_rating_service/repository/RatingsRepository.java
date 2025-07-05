package com.sarasavi.lib_rating_service.repository;

import com.sarasavi.lib_rating_service.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingsRepository extends JpaRepository<Ratings, Integer> {

    //find ratings by member id
    @Query("SELECT r FROM Ratings r WHERE r.memberId = ?1")
    List<Ratings> findByMemberId(int memberId);

}
