package com.maverick.newDAO;

import com.maverick.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author getById(int id);

    List<Author> getAll();

    long insert(Author author);

    long update(Author author);

    void delete(int id);
}
