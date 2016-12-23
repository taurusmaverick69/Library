package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Student;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.StudentDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateStudentDAO implements StudentDAO {

    @Override
    public List<Student> findAll() {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return session.createQuery("from Student ", Student.class).list();
        }
    }

    @Override
    public Student findById(int id) {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery("from Student where id = :id", Student.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public boolean save(Student student) {

//
//        Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
////            Group group = student.getGroup();
////            List list = session.createCriteria(Group.class)
////                    .setProjection(Projections.property("id"))
////                    .add(Restrictions.eq("name", group.getName()))
////                    .list();
////
////            if (!list.isEmpty()) {
////                group.setId((int) list.get(0));
////            }
//            session.save(student);
//            session.getTransaction().commit();
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }
        return true;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
//            session.beginTransaction();
//            int executeUpdate = session
//                    .createQuery("delete Student where id = :id")
//                    .setParameter("id", id)
//                    .executeUpdate();
//            session.getTransaction().commit();
//            return executeUpdate > 0;
        }
    }
}
