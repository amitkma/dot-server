package com.dot.backend.controller;

import com.dot.backend.domain.Feed;
import com.dot.backend.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dot/feeds")
public class FeedController {

    @Autowired
    private FeedRepository feedRepository;

    @GetMapping("")
    public List<Feed> feedsByTag(@RequestParam(value = "tag", required = false) String tag) {
        return (tag == null || tag.length() <= 0) ? feedRepository.findAll() : feedRepository.getFeedsByTag(tag);
    }
}
