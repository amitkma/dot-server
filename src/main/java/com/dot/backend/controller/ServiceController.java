package com.dot.backend.controller;

import com.dot.backend.domain.Feed;
import com.dot.backend.domain.Service;
import com.dot.backend.repository.FeedRepository;
import com.dot.backend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/api/services/{salon}")
    public List<Service> servicesBySalonAndCategory(@PathVariable String salon,
                                                    @RequestParam(value = "category", required = false) String category) {
        return (category == null || category.length() <= 0) ? serviceRepository.getServicesBySalon(salon)
                : serviceRepository.getServicesBySalonAndCategory(salon, category);
    }
}
