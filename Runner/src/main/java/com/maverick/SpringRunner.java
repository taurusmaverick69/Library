package com.maverick;

import com.maverick.domain.Author;
import com.maverick.service.AuthorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.lang.reflect.InvocationTargetException;

public class SpringRunner {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("model.cfg.xml");
        AuthorService service = context.getBean("author-service", AuthorService.class);
        Author myauthor = new Author(-1, "MYAUTHOR", "1212-1212");
        service.save(myauthor);
        myauthor.setFullName("NEW NAME");
        service.save(myauthor);

        try {
            System.out.println(service.getById(75));
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No Such ID");
        }
        System.out.println(service.getAll());
    }

}
