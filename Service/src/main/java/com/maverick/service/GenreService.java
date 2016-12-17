package com.maverick.service;

import com.maverick.domain.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    Genre findById(int id);

    void save(Genre genre);

    void delete(int id);
}
