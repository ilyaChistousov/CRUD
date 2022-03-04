package service.Impl;

import model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.Impl.PostRepoImpl;
import service.PostService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostServiceImplTest {

    private PostRepoImpl postRepo;
    private PostService postService;

    @BeforeEach
    void setPostRepo() {
        postRepo = mock(PostRepoImpl.class);
        postService = new PostServiceImpl(postRepo);
    }
    @Test
    void getByIdWithCorrectDataIdTest() {
        Post post = new Post();
        post.setId(1L);
        post.setContent("Content");

        when(postRepo.getById(eq(1L))).thenReturn(post);
        Post returnedPost = postService.getById(1L);

        assertEquals(post, returnedPost);
    }

    @Test
    void getByIdThrowNPEIfPostNotExistNullTest() {
        Post post = new Post();
        post.setId(1L);

        when(postRepo.getById(1L)).thenReturn(post);

        assertThrows(NullPointerException.class, () -> postService.getById(2L));
    }

    @Test
    void getAllTest() {
        List<Post> posts = new ArrayList<>(List.of(new Post(), new Post()));

        when(postRepo.getAll()).thenReturn(posts);

        assertEquals(2, postService.getAll().size());
    }

    @Test
    void saveWithCorrectDataReturn1Test() {
        Post savedPost = new Post();
        savedPost.setId(1L);
        savedPost.setContent("new Content");
        when(postRepo.save(any(Post.class), any(Long.class))).thenReturn(1);

        assertEquals(1, postService.save(savedPost, 1L));
    }

    @Test
    void saveThrowNPEWhenContentIsNullTest() {
        Post post = new Post();
        when(postRepo.save(eq(post), any(Long.class))).thenReturn(1);

        assertThrows(NullPointerException.class, () -> postService.save(post, 1L));
    }

    @Test
    void updateWithCorrectDataReturn1Test() {
        Post newPost = new Post();
        newPost.setContent("new Content");

        when(postRepo.update(eq(newPost), any(Long.class))).thenReturn(1);

        assertEquals(1, postService.update(newPost, 1L));
    }

    @Test
    void updateThrowNPEIfNewContentIsNullTest() {
        Post newPost = new Post();

        when(postRepo.update(eq(newPost), any(Long.class))).thenReturn(1);

        assertThrows(NullPointerException.class, () -> postService.update(newPost, 1L));
    }

    @Test
    void deleteSuccessIfPostExistTest() {
        Post deletedPost = new Post();
        deletedPost.setId(1L);

        when(postRepo.delete(1L)).thenReturn(1);

        assertEquals(1, postService.delete(1L));
    }

    @Test
    void deleteThrowNPEIfPostNotExist() {
        Post deletedPost = new Post();
        deletedPost.setId(1L);

        when(postRepo.delete(1L)).thenReturn(1);

        assertThrows(NullPointerException.class, () -> postService.delete(2L));
    }
}