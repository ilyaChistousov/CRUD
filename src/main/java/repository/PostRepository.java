package repository;

import model.Post;


public interface PostRepository extends GeneralRepository<Post, Long> {

    int save(Post post, Long writerId);
}
