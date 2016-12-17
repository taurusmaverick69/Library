package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Author;

import java.util.List;

public interface AuthorDAO {

    List<Author> findAll();

    Author findById(int id);

    boolean save(Author author);

    boolean update(Author author);

    boolean delete(Author author);
}
