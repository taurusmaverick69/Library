package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Librarian;

import java.util.List;

public interface LibrarianDAO {

    boolean insertLibrarian(Librarian librarian);

    boolean deleteLibrarian(Librarian librarian);

    List<Librarian> selectLibrarians();

    boolean updateLibrarian(Librarian librarian);

    boolean checkPassword(Librarian librarian);
}
