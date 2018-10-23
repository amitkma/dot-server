package com.dot.backend.controller;

import com.dot.backend.domain.Promotion;
import com.dot.backend.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dot/promotions")
public class PromotionController {

    @Autowired
    private PromotionRepository promotionRepository;

    @GetMapping("")
    public List<Promotion> getPromotions(){
        return promotionRepository.findAll();
    }
}
