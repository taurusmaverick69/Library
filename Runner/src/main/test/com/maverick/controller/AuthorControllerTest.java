package com.maverick.controller;


import com.maverick.domain.Author;
import com.maverick.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

    @Test
    public void getVehicleShouldReturnMakeAndModel() throws Exception {
        Author author = new Author();
        author.setId(1);
        author.setFullName("1");
        author.setYearsOfLife("1");
        given(this.authorService.findById(1)).willReturn(author);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get("localhost:8080/author/1");
        mvc.perform(mockHttpServletRequestBuilder.accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk()).andExpect((ResultMatcher) content().string("Honda Civic"));
    }
}