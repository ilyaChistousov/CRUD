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
    private static final String GET_ONE_SQL = "select * from posts where id = ?";
    private static final String GET_ALL_SQL = "select * from posts";
    private static final String SAVE_SQL = "insert into posts (writer_id, content, created, updated, post_status) " +
            "values(?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "update posts set content = ?, updated = ?, post_status = ? where id = ?";
    private static final String DELETE_SQL = "delete from posts where id = ?";
    private static final String GET_LABELS_SQL = "select * from labels l join posts p on l.post_id = p.id " +
            "where p.id = ?";

    @Override
    @SneakyThrows(SQLException.class)
    public Post getById(Long id) {
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ONE_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return buildPost(resultSet);
        }
    }

    @Override
    @SneakyThrows(SQLException.class)
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                posts.add(buildPost(resultSet));
            }
        }

        return posts;
    }


    @Override
    @SneakyThrows(SQLException.class)
    public int save(Post post, Long writerId) {
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL)) {
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
    public int update(Post newPost) {
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, newPost.getContent());
            statement.setTimestamp(2, newPost.getUpdated());
            statement.setString(3, newPost.getStatus().toString());
            statement.setLong(4, newPost.getId());
            return statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows(SQLException.class)
    public int delete(Long id) {
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }

    @SneakyThrows(SQLException.class)
    public static List<Label> getLabelsOfPost(Long postId) {
        List<Label> labels = new ArrayList<>();
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_LABELS_SQL)) {
            statement.setLong(1, postId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                labels.add(new Label(resultSet.getLong("id"),
                        resultSet.getString("name")));
            }
        }
        return labels;
    }

    @SneakyThrows(SQLException.class)
    public static Post buildPost(ResultSet resultSet) {
        return new Post(
                resultSet.getLong("id"),
                resultSet.getString("content"),
                resultSet.getTimestamp("created"),
                resultSet.getTimestamp("updated"),
                getLabelsOfPost(resultSet.getLong("id")),
                PostStatus.valueOf(resultSet.getString("post_status"))
        );
    }
}