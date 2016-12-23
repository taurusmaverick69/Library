package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Genre;

import java.util.List;

public interface GenreDAO {

    List<Genre> findAll();

    Genre findById(int id);

    boolean save(Genre genre);

    boolean update(Genre genre);

    void delete(int id);
}
