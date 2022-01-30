package com.example.springboottest.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpServerErrorException;

import com.example.springboottest.domain.Book;
import com.example.springboottest.service.BookRestService;

@ExtendWith({SpringExtension.class})
@RestClientTest(BookRestService.class)
public class BookRestControllerTest {

    @Autowired
    private BookRestService bookRestService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void restTest() {
        this.server.expect(requestTo("/rest/test"))
            .andRespond(withSuccess(new ClassPathResource("/test.json", getClass()), MediaType.APPLICATION_JSON));
        Book book = this.bookRestService.getRestBook();
        assertThat(book.getTitle()).isEqualTo("테스트");
    }

    @Test
    public void restErrorTest() {
        this.server.expect(requestTo("/rest/test")).andRespond(withServerError());
        assertThrows(HttpServerErrorException.class, () -> {
            bookRestService.getRestBook();
        });
    }



}