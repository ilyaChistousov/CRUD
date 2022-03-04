package repository;

import model.Post;

import java.util.List;

public interface PostRepository extends GeneralRepository<Post, Long> {

    int save(Post post, Long writerId);
}
