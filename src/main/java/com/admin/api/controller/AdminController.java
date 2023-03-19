package com.admin.api.controller;

import com.admin.api.exception.NoUsersReturnedException;
import com.admin.api.model.Post;
import com.admin.api.model.User;
import com.admin.api.model.UserWithPosts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Api(value = "Admin Api", tags = "Admin Api", produces = "application/json")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";

    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";

    private static final String USER_ERROR = "User API did not return any users";

    @ApiOperation(value = "get list of users with posts", response = UserWithPosts[].class, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Successfully retrieved list of users with posts"),
            @ApiResponse(code = 417, message = USER_ERROR),
            @ApiResponse(code = 500, message = "Internal Server Error: Exception message that occured")
    })
    @GetMapping("/users-with-posts")
    public List<UserWithPosts> getUsersWithPosts() {
        List<User> users = getUsersFromApi();
        List<Post> posts = getPostsFromApi();

        if(users==null || users.isEmpty()) {
            throw new NoUsersReturnedException(USER_ERROR);
        }

        List<Post> noPosts = new ArrayList<Post>();

        List<UserWithPosts> result = new ArrayList<>();
        Map<Integer,List<Post>> postsMap = null;
        if(posts!=null && !posts.isEmpty()) {
            postsMap = posts.stream().collect(Collectors.groupingBy(Post::getUserId));
        }

        if(postsMap==null) {
            users.forEach((user) -> {
                result.add(new UserWithPosts(user, noPosts));
            });
        } else {
            for (User user : users) {
                List<Post> userPosts = postsMap.get(user.getId());
                result.add(new UserWithPosts(user, (userPosts == null) ? noPosts : userPosts));
            }
        }
        return result;
    }

    /**
     * gets all the users received from user api
     * @return List<User> List of users
     */
    private List<User> getUsersFromApi() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity(USERS_URL, User[].class);
        return Arrays.asList(response.getBody());
    }

    /**
     * gets all the posts received from posts api
     * @return List<Post> List of all posts
     */
    private List<Post> getPostsFromApi() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Post[]> response = restTemplate.getForEntity(POSTS_URL, Post[].class);
        return Arrays.asList(response.getBody());
    }
}
