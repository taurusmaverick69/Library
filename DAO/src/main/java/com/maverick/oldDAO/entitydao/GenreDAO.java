package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Genre;

import java.util.List;

public interface GenreDAO {

    boolean insertGenre(Genre genre);

    boolean deleteGenre(Genre genre);

    List<Genre> selectGenres();

    boolean updateGenre(Genre genre);
}
