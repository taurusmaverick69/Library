package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Librarian;
import com.maverick.oldDAO.entitydao.LibrarianDAO;

import java.util.List;

public class MongoDBLibrarianDAO implements LibrarianDAO {
    @Override
    public boolean insertLibrarian(Librarian librarian) {
        return false;
    }

    @Override
    public boolean deleteLibrarian(Librarian librarian) {
        return false;
    }

    @Override
    public List<Librarian> selectLibrarians() {
        return null;
    }

    @Override
    public boolean updateLibrarian(Librarian librarian) {
        return false;
    }

    @Override
    public boolean checkPassword(Librarian librarian) {
        return false;
    }
}
