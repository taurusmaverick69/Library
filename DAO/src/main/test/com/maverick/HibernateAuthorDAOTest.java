package com.maverick;

import com.maverick.domain.Author;
import com.maverick.oldDAO.dbmsentitydao.hibernate.HibernateAuthorDAO;
import com.maverick.oldDAO.entitydao.AuthorDAO;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class HibernateAuthorDAOTest {

    @Mock
    AuthorDAO dao;

    @InjectMocks
    HibernateAuthorDAO hibernateAuthorDAO;

    @Spy
    List<Author> authors = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authors = getAuthors();
    }

    private List<Author> getAuthors() {

        Author author1 = new Author();
        author1.setId(1);
        author1.setFullName("Любимцев В. В.");

        Author author2 = new Author();
        author2.setId(47);
        author2.setFullName("Булгаков М. А.");

        authors.add(author1);
        authors.add(author2);

        return authors;
    }

    @Test
    public void findAllTest() {
        when(dao.findAll()).thenReturn(authors);
        Assert.assertEquals(dao.findAll(), authors);
    }

    @Test
    public void findById() {
        Author author = authors.get(0);
        when(dao.findById(anyInt())).thenReturn(author);
        Assert.assertEquals(dao.findById(author.getId()), author);
    }

    @Test
    public void save() {
        dao.save(new Author());
        verify(dao, times(5)).save(any(Author.class));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void nonExistsEntityTest() {
        hibernateAuthorDAO.findById(-1).getId();
    }

    @Test
    public void updateTest() {
        Author author = authors.get(0);
        when(dao.findById(anyInt())).thenReturn(author);
        hibernateAuthorDAO.update(author);
        verify(dao, atLeastOnce()).findById(anyInt());
    }

    @Test
    public void deleteTest() {
        doNothing().when(dao).delete(anyInt());
        dao.delete(anyInt());
        verify(dao, atLeastOnce()).delete(anyInt());
    }
}