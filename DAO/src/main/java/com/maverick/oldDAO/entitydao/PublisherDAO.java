package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Publisher;

import java.util.List;

public interface PublisherDAO {

    List<Publisher> findAll();

    Publisher findById(int id);

    boolean save(Publisher publisher);

    boolean update(Publisher publisher);

    void delete(int id);
}
