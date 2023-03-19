package com.admin.api.controller;

import com.admin.api.model.User;
import com.admin.api.model.UserWithPosts;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUsersWithPostsTest() {

        List<UserWithPosts> userWithPostsList = adminController.getUsersWithPosts();
        assertNotNull(userWithPostsList);
        assertFalse(userWithPostsList.isEmpty());
    }

    @Test
    public void getUsersWithEmptyPostsTest() {

        User user1 = new User();user1.setId(1);user1.setName("name");user1.setEmail("abc@abc.com");user1.setPhone("213");
        UserWithPosts userPost = new UserWithPosts(user1, new ArrayList<>());
        List<UserWithPosts> userPosts = List.of(userPost);

        AdminController mock = org.mockito.Mockito.mock(AdminController.class);

        when(mock.getUsersWithPosts()).thenReturn(userPosts);

        List<UserWithPosts> userWithPostsList = mock.getUsersWithPosts();
        assertNotNull(userWithPostsList);
        assertFalse(userWithPostsList.isEmpty());

        for(UserWithPosts userWithPosts: userWithPostsList) {
            assertTrue(userWithPosts.getPosts().isEmpty());
        }
    }

    @Test(expected = Exception.class)
    public void getUsersWithApiNullValueTest() {
        when(restTemplate.getForEntity(anyString(), eq(User[].class)))
                .then(null);

        adminController.getUsersWithPosts();
    }

    @Test(expected = Exception.class)
    public void getUsersWithApiErrorTest() {
        AdminController mock = org.mockito.Mockito.mock(AdminController.class);

        when(mock.getUsersWithPosts()).thenThrow(new RuntimeException("User API Error"));

        mock.getUsersWithPosts();
    }

    @Test(expected = Exception.class)
    public void getPostsWithApiErrorTest() {
        AdminController mock = org.mockito.Mockito.mock(AdminController.class);

        when(mock.getUsersWithPosts()).thenThrow(new RuntimeException("Post API Error"));

        mock.getUsersWithPosts();
    }
}