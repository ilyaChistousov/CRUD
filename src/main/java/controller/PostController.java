package controller;

import liquibase.pro.packaged.T;
import model.Post;
import model.PostStatus;
import repository.Impl.PostRepoImpl;
import service.Impl.PostServiceImpl;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class PostController {

    private final PostServiceImpl postService = new PostServiceImpl(new PostRepoImpl());

    public void addNewPost(String content, Long writerId) {
        Post newPost = new Post();
        newPost.setContent(content);
        newPost.setCreated(new Timestamp(System.currentTimeMillis()));
        newPost.setUpdated(new Timestamp(System.currentTimeMillis()));
        newPost.setStatus(PostStatus.ACTIVE);
        postService.save(newPost, writerId);
    }

    public Post getPost(Long id) {
        return postService.getById(id);
    }

    public List<Post> getAllPosts() {
        return postService.getAll();
    }

    public void updatePost(String newContent, Long id) {
        Post newPost = new Post();
        newPost.setContent(newContent);
        newPost.setUpdated(new Timestamp(System.currentTimeMillis()));
        newPost.setStatus(PostStatus.UNDER_REVIEW);
        postService.update(newPost, id);
    }

    public void deletePost(Long id) {
        postService.delete(id);
    }
}
