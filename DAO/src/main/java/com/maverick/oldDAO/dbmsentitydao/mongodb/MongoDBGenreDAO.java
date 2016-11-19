package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Genre;
import com.maverick.oldDAO.entitydao.GenreDAO;

import java.util.List;

public class MongoDBGenreDAO implements GenreDAO {
    @Override
    public boolean insertGenre(Genre genre) {
        return false;
    }

    @Override
    public boolean deleteGenre(Genre genre) {
        return false;
    }

    @Override
    public List<Genre> selectGenres() {
        return null;
    }

    @Override
    public boolean updateGenre(Genre genre) {
        return false;
    }
}
