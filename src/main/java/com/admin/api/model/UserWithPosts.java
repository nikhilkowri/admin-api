package com.admin.api.model;

import java.util.List;

public class UserWithPosts {
    private User user;
    private List<Post> posts;

    public UserWithPosts(User user, List<Post> posts) {
        this.user = user;
        this.posts = posts;
    }

    public User getUser() {
        return user;
    }

    public List<Post> getPosts() {
        return posts;
    }
}




