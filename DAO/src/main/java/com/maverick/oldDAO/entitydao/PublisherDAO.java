package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Publisher;

import java.util.List;

public interface PublisherDAO {

    boolean insertPublisher(Publisher publisher);

    boolean deletePublisher(Publisher publisher);

    List<Publisher> selectPublishers();

    boolean updatePublisher(Publisher publisher);
}
