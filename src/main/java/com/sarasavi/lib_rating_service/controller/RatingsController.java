package com.sarasavi.lib_rating_service.controller;

import com.sarasavi.lib_rating_service.dto.RatingsDTO;
import com.sarasavi.lib_rating_service.service.RatingsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
public class RatingsController {

    @Autowired
    private RatingsService ratingsService;

    @Autowired
    private ModelMapper modelMapper;

    // Get all ratings
    @GetMapping(path = "/ratings")
    public List<RatingsDTO> getAllRatings() {
        return ratingsService.getAllRatings();
    }

    // Get ratings by rating id
    @GetMapping(path = "/ratings/{ratingId}")
    public RatingsDTO getRatingsById(@PathVariable int ratingId) {
        return ratingsService.getRatingsById(ratingId);
    }

    //view ratings by member id
    @GetMapping(path = "/ratings/member/{memberId}")
    public List<RatingsDTO> getRatingsByMemberId(@PathVariable int memberId) {
        return ratingsService.getRatingsByMemberId(memberId);
    }

    // view ratings by book id
    @GetMapping(path = "/ratings/book/{bookId}")
    public List<RatingsDTO> getRatingsByBookId(@PathVariable int bookId) {
        return ratingsService.getRatngsByBookId(bookId);
    }

    // Add a new rating
    @PostMapping(path = "/ratings/add")
    public RatingsDTO addRating(@RequestBody RatingsDTO ratingsDTO) {
        return ratingsService.addRating(ratingsDTO);
    }
}
