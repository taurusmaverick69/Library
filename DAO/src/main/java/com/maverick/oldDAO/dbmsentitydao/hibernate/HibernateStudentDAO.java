package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Student;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.StudentDAO;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class HibernateStudentDAO implements StudentDAO {
    @Override
    public boolean insertStudent(Student student) {
        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
//            Group group = student.getGroup();
//            List list = session.createCriteria(Group.class)
//                    .setProjection(Projections.property("id"))
//                    .add(Restrictions.eq("name", group.getName()))
//                    .list();
//
//            if (!list.isEmpty()) {
//                group.setId((int) list.get(0));
//            }
            session.save(student);
            session.getTransaction().commit();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return true;
    }

    @Override
    public boolean deleteStudent(Student student) {
        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return true;

    }

    @Override
    public List<Student> selectStudents() {
        Session session = HibernateDAOFactory.openSession();
        List<Student> students = new ArrayList<>();
        try {
            session.beginTransaction();
            students.addAll(session.createCriteria(Student.class).list());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return students;
    }


    @Override
    public boolean updateStudent(Student student) {
        return false;
    }
}
