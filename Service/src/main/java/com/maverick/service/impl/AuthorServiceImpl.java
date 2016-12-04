package com.maverick.service.impl;

import com.maverick.domain.Author;
import com.maverick.repository.AuthorRepository;
import com.maverick.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(int id) {
        return authorRepository.findOne(id);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void delete(int id) {
        authorRepository.delete(id);
    }
}
