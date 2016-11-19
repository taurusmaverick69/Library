package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Publisher;
import com.maverick.oldDAO.entitydao.PublisherDAO;

import java.util.List;

public class MongoDBPublisherDAO implements PublisherDAO {
    @Override
    public boolean insertPublisher(Publisher publisher) {
        return false;
    }

    @Override
    public boolean deletePublisher(Publisher publisher) {
        return false;
    }

    @Override
    public List<Publisher> selectPublishers() {
        return null;
    }

    @Override
    public boolean updatePublisher(Publisher publisher) {
        return false;
    }
}
