package service;

import model.Post;

public interface PostService extends GeneralService <Post, Long>{

    int save(Post post, Long writerId);
}
