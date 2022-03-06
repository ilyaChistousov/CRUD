package service.Impl;

import lombok.SneakyThrows;
import model.Post;
import repository.PostRepository;
import service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {
    private final PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public Post getById(Long id) {
        Post post = postRepo.getById(id);
        if(post == null) {
            throw new NullPointerException("Post not exits");
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        return postRepo.getAll();
    }

    @Override
    public int save(Post post, Long writerId) {
        if(post.getContent().isEmpty()) {
            throw new NullPointerException("Post shouldn`t empty");
        }
        return postRepo.save(post, writerId);
    }

    @SneakyThrows
    @Override
    public int update(Post newPost, Long id) {
        if(newPost.getContent().isEmpty()) {
            throw new NullPointerException("New content shouldn`t be empty");
        }
        return postRepo.update(newPost, id);
    }

    @Override
    public int delete(Long id) {
        int result = postRepo.delete(id);
        if(result == 0) {
            throw new NullPointerException("Post not exist");
        }
        return result;
    }
}
