package com.sarasavi.lib_rating_service.controller;

import com.sarasavi.lib_rating_service.dto.RatingsDTO;
import com.sarasavi.lib_rating_service.service.RatingsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
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


}
