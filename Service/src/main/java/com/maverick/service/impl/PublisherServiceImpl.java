package com.maverick.service.impl;

import com.maverick.domain.Publisher;
import com.maverick.repository.PublisherRepository;
import com.maverick.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher findById(int id) {
        return publisherRepository.findOne(id);
    }

    @Override
    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public void delete(int id) {
        publisherRepository.delete(id);
    }
}
