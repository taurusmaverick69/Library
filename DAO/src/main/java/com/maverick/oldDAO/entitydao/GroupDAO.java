package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Group;

import java.util.List;

public interface GroupDAO {

    List<Group> findAll();

    Group findById(int id);

    boolean save(Group group);

    boolean update(Group group);

    boolean delete(Group group);
}
