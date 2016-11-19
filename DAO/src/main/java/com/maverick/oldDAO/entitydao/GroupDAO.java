package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Group;

import java.util.List;

public interface GroupDAO {

    boolean insertGroup(Group group);

    boolean deleteGroup(Group group);

    List<Group> selectGroups();

    boolean updateGroup(Group group);

}
