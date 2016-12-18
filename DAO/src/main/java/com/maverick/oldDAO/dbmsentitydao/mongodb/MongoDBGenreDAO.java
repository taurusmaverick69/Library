package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Genre;
import com.maverick.oldDAO.entitydao.GenreDAO;

import java.util.List;

public class MongoDBGenreDAO implements GenreDAO {
    @Override
    public boolean save(Genre genre) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Genre> findAll() {
        return null;
    }

    @Override
    public Genre findById(int id) {
        return null;
    }

    @Override
    public boolean update(Genre genre) {
        return false;
    }
}
