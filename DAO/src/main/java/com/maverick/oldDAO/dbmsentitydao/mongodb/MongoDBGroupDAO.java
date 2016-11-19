package com.maverick.oldDAO.dbmsentitydao.mongodb;

import com.maverick.domain.Group;
import com.maverick.oldDAO.entitydao.GroupDAO;

import java.util.List;

public class MongoDBGroupDAO implements GroupDAO {
    @Override
    public boolean insertGroup(Group group) {
        return false;
    }

    @Override
    public boolean deleteGroup(Group group) {
        return false;
    }

    @Override
    public List<Group> selectGroups() {
        return null;
    }

    @Override
    public boolean updateGroup(Group group) {
        return false;
    }
}
