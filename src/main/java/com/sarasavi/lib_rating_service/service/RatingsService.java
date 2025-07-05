package com.sarasavi.lib_rating_service.service;

import com.sarasavi.lib_rating_service.dto.RatingsDTO;
import com.sarasavi.lib_rating_service.entity.Ratings;
import com.sarasavi.lib_rating_service.repository.RatingsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RatingsService {
    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    //get all ratings
    public List<RatingsDTO> getAllRatings() {
        List<Ratings> ratingsList = ratingsRepository.findAll();
        return ratingsList.stream()
                .map(ratings -> modelMapper.map(ratings, RatingsDTO.class))
                .toList();
    }
    // get ratings by: member id
    public List<RatingsDTO> getRatingsByMemberId(int memberId) {
        List<Ratings> ratingsList = ratingsRepository.findRatingsByMemberId(memberId);
        return ratingsList.stream()
                .map(ratings -> modelMapper.map(ratings, RatingsDTO.class))
                .toList();
    }

    
}
