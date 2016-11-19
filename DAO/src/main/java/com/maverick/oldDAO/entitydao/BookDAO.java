package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Book;

import java.util.List;

public interface BookDAO {

    boolean insertBook(Book book);

    boolean deleteBook(Book book);

    List<Book> selectBooks();

    boolean updateBook(Book book);

    List<Book> searchBook(Book book);

    int selectAmount(Book book);

    boolean isOrdered(Book book);


}
