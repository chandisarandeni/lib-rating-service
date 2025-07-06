package com.sarasavi.lib_rating_service.service;

import com.sarasavi.lib_rating_service.dto.RatingsDTO;
import com.sarasavi.lib_rating_service.entity.Ratings;
import com.sarasavi.lib_rating_service.repository.RatingsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class RatingsService {
    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    // get all ratings
    public List<RatingsDTO> getAllRatings() {
        List<Ratings> ratingsList = ratingsRepository.findAll();
        return ratingsList.stream()
                .map(ratings -> modelMapper.map(ratings, RatingsDTO.class))
                .toList();
    }

    //get ratings by : rating id
    public RatingsDTO getRatingsById(int ratingId) {
        Ratings ratings = ratingsRepository.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + ratingId));
        return modelMapper.map(ratings, RatingsDTO.class);
    }

    // get ratings by: member id
    public List<RatingsDTO> getRatingsByMemberId(int memberId) {
        List<Ratings> ratingsList = ratingsRepository.findRatingsByMemberId(memberId);
        return ratingsList.stream()
                .map(ratings -> modelMapper.map(ratings, RatingsDTO.class))
                .toList();
    }

    // get ratings by: book id
    public List<RatingsDTO> getRatngsByBookId(int bookId) {
        List<Ratings> ratingsList = ratingsRepository.findRatingsByBookId(bookId);
        return ratingsList.stream()
                .map(ratings -> modelMapper.map(ratings, RatingsDTO.class))
                .toList();
    }

    // add new rating
    public RatingsDTO addRating(RatingsDTO ratingsDTO) {
        // 1. Save the new rating
        Ratings ratings = modelMapper.map(ratingsDTO, Ratings.class);
        Ratings savedRatings = ratingsRepository.save(ratings);

        // 2. Calculate the new average rating for the book
        Double avgRating = ratingsRepository.findAverageRatingByBookId(savedRatings.getBookId());

        // 3. Build the URI with query parameter
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/api/v1/books/{bookId}/ratings")
                .queryParam("avgRating", avgRating)
                .buildAndExpand(savedRatings.getBookId())
                .toUri();

        // 4. Send PUT request to Book Service
        try {
            restTemplate.exchange(uri, HttpMethod.PUT, null, Void.class);
        } catch (RestClientException e) {
            System.err.println("Failed to update book rating: " + e.getMessage());
        }

        return modelMapper.map(savedRatings, RatingsDTO.class);
    }

    // update existing rating and recalculate average rating
    public RatingsDTO updateRating(int ratingId, RatingsDTO ratingsDTO) {
        Ratings existingRatings = ratingsRepository.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + ratingId));

        // Ensure ID is not overwritten
        ratingsDTO.setRatingId(ratingId);
        modelMapper.map(ratingsDTO, existingRatings);

        Ratings updatedRatings = ratingsRepository.save(existingRatings);

        // Recalculate the average rating for the book
        Double avgRating = ratingsRepository.findAverageRatingByBookId(updatedRatings.getBookId());

        // Build the URI with query parameter
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/api/v1/books/{bookId}/ratings")
                .queryParam("avgRating", avgRating)
                .buildAndExpand(updatedRatings.getBookId())
                .toUri();

        // Send PUT request to Book Service
        try {
            restTemplate.exchange(uri, HttpMethod.PUT, null, Void.class);
        } catch (RestClientException e) {
            System.err.println("Failed to update book rating: " + e.getMessage());
        }

        return modelMapper.map(updatedRatings, RatingsDTO.class);
    }


}
