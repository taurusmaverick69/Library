package com.maverick.controller;

import com.maverick.domain.Publisher;
import com.maverick.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public List<Publisher> findAll() {
        return publisherService.findAll();
    }

    @GetMapping("/{id}")
    public Publisher findById(@PathVariable("id") int id) {
        return publisherService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Publisher publisher) {
        publisherService.save(publisher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        publisherService.delete(id);
    }
}