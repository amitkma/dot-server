package com.dot.backend.controller;

import com.dot.backend.domain.Feed;
import com.dot.backend.domain.Inspiration;
import com.dot.backend.repository.FeedRepository;
import com.dot.backend.repository.InspirationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InspirationController {

    @Autowired
    InspirationRepository inspirationRepository;

    @GetMapping("/api/inspirations")
    public List<Inspiration> inspirations(@RequestParam(value = "category", required = false) String category) {
        return (category == null || category.length()<=0) ? inspirationRepository.findAll()
                : inspirationRepository.getInspirationByCategory(category);
    }
}
