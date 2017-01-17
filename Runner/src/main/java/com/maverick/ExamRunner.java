package com.maverick;

import com.maverick.domain.exam.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExamRunner {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("exam.cfg.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
    }
}
