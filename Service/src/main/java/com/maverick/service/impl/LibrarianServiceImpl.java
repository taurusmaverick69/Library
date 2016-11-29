package com.maverick.service.impl;

import com.maverick.service.LibrarianService;
import com.maverick.domain.Librarian;
import com.maverick.repository.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    @Autowired
    private LibrarianRepository librarianRepository;

    @Override
    public List<Librarian> findAll() {
        return librarianRepository.findAll();
    }

    @Override
    public Librarian findById(int id) {
        return librarianRepository.findOne(id);
    }

    @Override
    public void save(Librarian librarian) {
        librarianRepository.save(librarian);
    }

    @Override
    public void delete(int id) {
        librarianRepository.delete(id);
    }
}
