package repository.Impl;

import lombok.SneakyThrows;
import model.Label;
import model.Post;
import model.PostStatus;
import repository.PostRepository;
import util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepoImpl implements PostRepository {

    @Override
    @SneakyThrows(SQLException.class)
    public Post getById(Long id) {
        Post returnedPost = new Post();
        String sql = "select * from posts where id = ?";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            returnedPost.setId(resultSet.getLong("id"));
            returnedPost.setContent(resultSet.getString("content"));
            returnedPost.setCreated(resultSet.getTimestamp("created"));
            returnedPost.setUpdated(resultSet.getTimestamp("updated"));
            returnedPost.setLabels(getLabelsOfPost(resultSet.getLong("id")));
            returnedPost.setStatus(PostStatus.valueOf(resultSet.getString("post_status")));
        }

        return returnedPost;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        String sql = "select * from posts";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                posts.add(new Post(resultSet.getLong("id"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created"),
                        resultSet.getTimestamp("updated"),
                        getLabelsOfPost(resultSet.getLong("id")),
                        PostStatus.valueOf(resultSet.getString("post_status"))));
            }
        }

        return posts;
    }


    @Override
    @SneakyThrows(SQLException.class)
    public int save(Post post, Long writerId) {
        String sql = "insert into posts (writer_id, content, created, updated, post_status) " +
                "values(?, ?, ?, ?, ?)";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, writerId);
            statement.setString(2, post.getContent());
            statement.setTimestamp(3, post.getCreated());
            statement.setTimestamp(4, post.getCreated());
            statement.setString(5, post.getStatus().toString());
            return statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows(SQLException.class)
    public int update(Post newPost, Long id) {
        String sql = "update posts set content = ? updated = ? post_status = ? where id = ?";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newPost.getContent());
            statement.setTimestamp(2, newPost.getUpdated());
            statement.setString(3, newPost.getStatus().toString());
            statement.setLong(4, id);
            return statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows(SQLException.class)
    public int delete(Long id) {
        String sql = "delete from posts where id = ?";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }

    @SneakyThrows(SQLException.class)
    public static List<Label> getLabelsOfPost(Long postId) {
        List<Label> labels = new ArrayList<>();
        String sql = "select * from labels l join posts p on l.post_id = p.id where p.id = ?";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, postId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                labels.add(new Label(resultSet.getLong("id"),
                        resultSet.getString("name")));
            }
        }

        return labels;
    }
}