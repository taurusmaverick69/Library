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

    @RequestMapping
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @RequestMapping("/{id}")
    public Genre findById(@PathVariable("id") int id) {
        return genreService.findById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@ModelAttribute("book") Genre genre) {
        genreService.save(genre);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        genreService.delete(id);
    }

}
