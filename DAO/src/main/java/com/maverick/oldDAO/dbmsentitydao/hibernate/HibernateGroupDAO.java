package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Group;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.GroupDAO;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class HibernateGroupDAO implements GroupDAO {
    @Override
    public boolean insertGroup(Group group) {
        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
            session.save(group);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return true;
    }

    @Override
    public boolean deleteGroup(Group group) {
        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
//            try {
//                session.createQuery("delete Book where id = :ID").setParameter("ID", book.getId()).executeUpdate();
//            } catch (ConstraintViolationException constraintViolationException) {
//                return false;
//            }
            session.delete(group);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return true;
    }

    @Override
    public List<Group> selectGroups() {
        Session session = HibernateDAOFactory.openSession();
        List<Group> groups = new ArrayList<>();

        try {
            session.beginTransaction();
            groups.addAll(session.createCriteria(Group.class).list());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return groups;
    }

    @Override
    public boolean updateGroup(Group group) {
        return false;
    }
}
