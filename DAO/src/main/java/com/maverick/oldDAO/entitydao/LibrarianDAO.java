package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Librarian;

import java.util.List;

public interface LibrarianDAO {

    List<Librarian> findAllWithOrders();

    Librarian findById(int id);

    boolean save(Librarian librarian);

    boolean update(Librarian librarian);

    boolean delete(int id);
}
