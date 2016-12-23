package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Publisher;
import com.maverick.oldDAO.entitydao.PublisherDAO;

import java.util.List;

public class MongoDBPublisherDAO implements PublisherDAO {
    @Override
    public boolean save(Publisher publisher) {
        return false;
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public List<Publisher> findAll() {
        return null;
    }

    @Override
    public Publisher findById(int id) {
        return null;
    }

    @Override
    public boolean update(Publisher publisher) {
        return false;
    }
}
