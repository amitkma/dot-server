package com.dot.backend.controller;

import com.dot.backend.domain.Service;
import com.dot.backend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dot/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/{salon}")
    public List<Service> servicesBySalonAndCategory(@PathVariable String salon,
                                                    @RequestParam(value = "category", required = false) String category) {
        return (category == null || category.length() <= 0) ? serviceRepository.findBySalon(salon)
                : serviceRepository.findBySalonAndCategory(salon, category);
    }
}
