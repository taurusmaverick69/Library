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
    public void delete(int id) {
    }

    @Override
    public List<Librarian> findAllWithOrders() {
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
