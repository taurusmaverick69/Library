package com.maverick.service;

import com.maverick.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(int id);

    void save(Author author);

    void delete(int id);
}
