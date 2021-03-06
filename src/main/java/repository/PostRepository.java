package repository;

import model.Post;


public interface PostRepository extends GeneralRepository<Post, Long> {

    int save(Post entity, Long id);
}
