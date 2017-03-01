package com.maverick.controller;

import com.maverick.domain.Genre;
import com.maverick.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    public Genre findById(@PathVariable("id") int id) {
        return genreService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Genre genre) {
        genreService.save(genre);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        genreService.delete(id);
    }
}