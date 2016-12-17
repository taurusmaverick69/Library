package com.maverick.service;

import com.maverick.domain.Librarian;

import java.util.List;

public interface LibrarianService {

    List<Librarian> findAll();

    Librarian findById(int id);

    void save(Librarian librarian);

    void delete(int id);
}
