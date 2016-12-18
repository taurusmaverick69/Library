package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Librarian;
import com.maverick.oldDAO.entitydao.LibrarianDAO;

import java.util.List;

public class MongoDBLibrarianDAO implements LibrarianDAO {
    @Override
    public boolean save(Librarian librarian) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Librarian> findAll() {
        return null;
    }

    @Override
    public Librarian findById(int id) {
        return null;
    }

    @Override
    public boolean update(Librarian librarian) {
        return false;
    }
}
