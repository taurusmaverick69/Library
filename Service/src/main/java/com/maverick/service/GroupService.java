package com.maverick.service;

import com.maverick.domain.Group;

import java.util.List;

public interface GroupService {

    List<Group> findAll();

    Group findById(int id);

    void save(Group group);

    void delete(int id);

}
