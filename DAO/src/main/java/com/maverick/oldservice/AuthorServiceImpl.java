package com.maverick.oldservice;

import com.maverick.newDAO.AuthorDao;
import com.maverick.domain.Author;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AuthorServiceImpl implements AuthorService {

    private Logger logger = Logger.getLogger("ServiceLogging");
    private AuthorDao dao;

    {
        FileHandler fh = null;
        try {
            fh = new FileHandler("addons/ServiceLogging.log", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fh != null) {
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
        }
    }

    @Override
    public Author getById(int id) {
        logger.info("Getting author by id: " + id);
        return dao.getById(id);
    }

    @Override
    public List<Author> getAll() {
        logger.info("Getting all authors");
        return dao.getAll();
    }

    @Override
    public void save(Author author) {
        logger.info("Saving author " + author);
        dao.insert(author);
    }

    @Override
    public void delete(int id) {
        logger.info("Removing author by id: " + id);
        dao.delete(id);
    }

    public AuthorDao getDao() {
        return dao;
    }

    public void setDao(AuthorDao dao) {
        this.dao = dao;
    }
}
