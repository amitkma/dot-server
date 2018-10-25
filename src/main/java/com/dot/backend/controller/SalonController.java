package com.dot.backend.controller;

import com.dot.backend.domain.Salon;
import com.dot.backend.exceptions.NotFoundException;
import com.dot.backend.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("dot/salons")
public class SalonController {

    @Autowired
    private SalonRepository salonRepository;

    @GetMapping("")
    public List<Salon> getSalons() {
        return salonRepository.findAll();
    }

    @GetMapping("/{id}")
    public Salon getSalonById(@PathVariable String id) {
        Optional<Salon> salon = salonRepository.findById(id);
        if (salon.isPresent()) {
            return salon.get();
        } else {
            throw new NotFoundException("Invalid resource id");
        }
    }
}
