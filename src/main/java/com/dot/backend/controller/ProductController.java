package com.dot.backend.controller;

import com.dot.backend.domain.Product;
import com.dot.backend.domain.Service;
import com.dot.backend.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dot/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("")
    public List<Product> servicesBySalonAndCategory(@RequestParam(value = "category", required = false) String category) {
        return (category == null || category.length() <= 0) ? productRepository.findAll()
                : productRepository.findByCategory(new ObjectId(category));
    }
}
