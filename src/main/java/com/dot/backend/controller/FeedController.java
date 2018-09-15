package com.dot.backend.controller;

import com.dot.backend.domain.Feed;
import com.dot.backend.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedController {

    @Autowired
    FeedRepository feedRepository;

    @GetMapping("/api/feeds")
    public List<Feed> feeds() {
        return feedRepository.findAll();
    }

    @GetMapping("/api/feeds/{tag}")
    public List<Feed> feedsByTag(@PathVariable String tag) {
        return feedRepository.getFeedsByTag(tag);
    }
}
