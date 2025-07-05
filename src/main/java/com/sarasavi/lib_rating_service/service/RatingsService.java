package com.sarasavi.lib_rating_service.service;

import com.sarasavi.lib_rating_service.repository.RatingsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingsService {
    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;
}
