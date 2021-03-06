package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Book;

import java.util.List;

public interface BookDAO {

    List<Book> findAll();

    Book findById(int id);

    boolean save(Book book);

    boolean update(Book book);

    void delete(int id);

    int selectAmount(Book book);
}
