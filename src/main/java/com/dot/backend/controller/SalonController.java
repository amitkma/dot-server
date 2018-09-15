package com.dot.backend.controller;

import com.dot.backend.domain.Salon;
import com.dot.backend.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalonController {

    @Autowired
    private SalonRepository salonRepository;

    @RequestMapping("secure/salons")
    public List<Salon> greeting() {
        return salonRepository.findAll();
    }
}
