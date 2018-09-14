package com.dot.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class SalonController {

    @Autowired
    private SalonRepository salonRepository;

    @RequestMapping("/salons")
    public List<Salon> greeting() {
        return salonRepository.findAll();
    }
}
