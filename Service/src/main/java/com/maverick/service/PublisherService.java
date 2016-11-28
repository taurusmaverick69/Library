package com.maverick.service;

import com.maverick.domain.Publisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> findAll();

    Publisher findById(int id);

    void save(Publisher publisher);

    void delete(int id);

}
