package com.maverick;

import com.maverick.domain.exam.User;
import com.maverick.examDAO.HibernateDAOFactory;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ExamRunner {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("exam.cfg.xml");
        User user = context.getBean("user", User.class);
        System.err.println(user);

        Session session = HibernateDAOFactory.getSessionFactory().openSession();
        List<User> usersByKharkiv = session.createNamedQuery("findUsersByKharkiv", User.class).list();

        usersByKharkiv.forEach(System.err::println);
    }
}