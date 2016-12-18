package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Group;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.GroupDAO;
import org.hibernate.Session;

import java.util.List;

public class HibernateGroupDAO implements GroupDAO {


    @Override
    public List<Group> findAll() {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return session.createQuery("from Group ", Group.class).list();
        }
    }

    @Override
    public Group findById(int id) {
        return null;
    }

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
    public boolean update(Group group) {
        return false;
    }

    @Override
    public boolean delete(int id) {
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
}
