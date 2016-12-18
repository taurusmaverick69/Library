package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Group;
import com.maverick.oldDAO.entitydao.GroupDAO;

import java.util.ArrayList;
import java.util.List;

public class HibernateGroupDAO implements GroupDAO {
    @Override
    public boolean save(Group group) {
        //   Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.save(group);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }
        return true;
    }

    @Override
    public boolean delete(Group group) {
//        Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
////            try {
////                session.createQuery("delete Book where id = :ID").setParameter("ID", book.getId()).executeUpdate();
////            } catch (ConstraintViolationException constraintViolationException) {
////                return false;
////            }
//            session.delete(group);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }
        return true;
    }

    @Override
    public List<Group> findAll() {
        // Session session = HibernateDAOFactory.openSession();
        List<Group> groups = new ArrayList<>();

//        try {
//            session.beginTransaction();
//            groups.addAll(session.createCriteria(Group.class).list());
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//
//       }
        return groups;
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
