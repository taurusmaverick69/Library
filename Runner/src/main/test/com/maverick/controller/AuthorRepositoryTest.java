package com.maverick.controller;

import com.maverick.domain.Author;
import com.maverick.repository.AuthorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void findByIdAndAssert() {
        authorRepository.save(new Author(1, "1", "1"));
        Author author = authorRepository.findOne(1);
        Assert.assertEquals(author.getFullName(), "1");
        Assert.assertEquals(author.getYearsOfLife(), "1");
    }
}

