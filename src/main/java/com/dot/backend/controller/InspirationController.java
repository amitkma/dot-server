package com.dot.backend.controller;

import com.dot.backend.domain.Inspiration;
import com.dot.backend.repository.InspirationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dot")
public class InspirationController {

    @Autowired
    private InspirationRepository inspirationRepository;

    @GetMapping("/inspirations")
    public List<Inspiration> inspirations(@RequestParam(value = "category", required = false) String category) {
        return (category == null || category.length()<=0) ? inspirationRepository.findAll()
                : inspirationRepository.getInspirationByCategory(category);
    }
}
