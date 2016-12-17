package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Group;
import com.maverick.oldDAO.entitydao.GroupDAO;

import java.util.List;

public class MongoDBGroupDAO implements GroupDAO {
    @Override
    public boolean save(Group group) {
        return false;
    }

    @Override
    public boolean delete(Group group) {
        return false;
    }

    @Override
    public List<Group> findAll() {
        return null;
    }

    @Override
    public Group findById(int id) {
        return null;
    }

    @Override
    public boolean update(Group group) {
        return false;
    }
}
