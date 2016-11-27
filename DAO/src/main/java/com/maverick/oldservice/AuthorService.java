package com.maverick.oldservice;


import com.maverick.domain.Author;

import java.util.List;

public interface AuthorService {

    Author getById(int id);

    List<Author> getAll();

    void save(Author author);

    void delete(int id);

}
