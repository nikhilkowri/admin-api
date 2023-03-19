package com.admin.api.integration;


import com.admin.api.AdminApiApplication;
import com.admin.api.model.UserWithPosts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AdminApiApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AdminControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldReturnUserWithPostsWhenApiCalled() {

        UserWithPosts[] userPosts = testRestTemplate.getForObject("http://localhost:"+port+"/admin/users-with-posts", UserWithPosts[].class);
        assertThat(userPosts).isNotNull();
        assertThat(userPosts.length).isEqualTo(10);
    }
}
