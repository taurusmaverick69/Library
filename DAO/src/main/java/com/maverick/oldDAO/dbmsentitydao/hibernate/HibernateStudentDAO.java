package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Student;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.StudentDAO;
import org.hibernate.Session;

import java.util.List;

public class HibernateStudentDAO implements StudentDAO {
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
    public boolean delete(Student student) {
//        Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.delete(student);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }
        return true;

    }

    @Override
    public List<Student> findAll() {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return session.createQuery("from Student ", Student.class).list();
        }
    }

    @Override
    public Student findById(int id) {
        return null;
    }


    @Override
    public boolean update(Student student) {
        return false;
    }
}
