package com.maverick.controller;

import com.maverick.domain.Book;
import com.maverick.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @RequestMapping("/{id}")
    public Book findById(@PathVariable("id") int id) {
        return bookService.findById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@ModelAttribute("book") Book book) {
        bookService.save(book);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        bookService.delete(id);
    }
}
