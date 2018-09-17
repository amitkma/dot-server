package com.dot.backend.controller;

import com.dot.backend.domain.Salon;
import com.dot.backend.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dot")
public class SalonController {

    @Autowired
    private SalonRepository salonRepository;

    @GetMapping("/salons")
    public List<Salon> salons() {
        return salonRepository.findAll();
    }
}
