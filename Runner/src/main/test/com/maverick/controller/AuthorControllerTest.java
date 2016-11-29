package com.maverick.controller;

import com.maverick.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

//    @Autowired
//    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

//    @Mock
//    private AuthorService authorService;
//
//    @InjectMocks
//    AuthorController authorController;

    @Test
    public void findAll() throws Exception {

        System.out.println("AuthorControllerTest.findAll");
//
//        List<Author> all = authorController.findAll();
//
//        System.out.println("all = " + all);

    }
}