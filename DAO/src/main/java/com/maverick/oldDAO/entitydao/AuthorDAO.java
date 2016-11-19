package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Author;

import java.util.List;

public interface AuthorDAO {

    boolean insertAuthor(Author author);

    boolean deleteAuthor(Author author);

    List<Author> selectAuthors();

    boolean updateAuthor(Author author);
}
