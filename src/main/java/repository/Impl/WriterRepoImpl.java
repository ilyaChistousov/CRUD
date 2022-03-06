package repository.Impl;

import lombok.SneakyThrows;
import model.Post;
import model.PostStatus;
import model.Writer;
import repository.WriterRepository;
import util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WriterRepoImpl implements WriterRepository {
    private static final String GET_ONE_SQL = "select * from writers where id = ?";
    private static final String GET_ALL_SQL = "select * from writers";
    private static final String SAVE_SQL = "insert into writers (first_name, last_name) values (?, ?)";
    private static final String UPDATE_SQL = "update writers set first_name = ?, last_name = ? where id = ?";
    private static final String DELETE_SQL = "delete from writers where id = ?";
    private static final String GET_POSTS_SQL = "select * from posts p join writers w on p.writer_id = w.id " +
            "where w.id = ?";

    @Override
    @SneakyThrows(SQLException.class)
    public Writer getById(Long id) {

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ONE_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return buildWriter(resultSet);
        }
    }

    @Override
    @SneakyThrows(SQLException.class)
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                writers.add(buildWriter(resultSet));
            }
        }

        return writers;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public int save(Writer writer) {
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL)) {
            statement.setString(1, writer.getFirstName());
            statement.setString(2, writer.getLastName());
            return statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows(SQLException.class)
    public int update(Writer writer, Long id) {
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, writer.getFirstName());
            statement.setString(2, writer.getLastName());
            statement.setLong(3, id);
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
    private List<Post> getPostsOfWriter(Long writerId) {
        List<Post> posts = new ArrayList<>();

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_POSTS_SQL)) {
            statement.setLong(1, writerId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                posts.add(new Post(resultSet.getLong("id"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created"),
                        resultSet.getTimestamp("updated"),
                        PostRepoImpl.getLabelsOfPost((resultSet.getLong("id"))),
                        PostStatus.valueOf(resultSet.getString("post_status"))));
            }
        }
        return posts;
    }

    @SneakyThrows(SQLException.class)
    private Writer buildWriter(ResultSet resultSet) {
        return new Writer(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                getPostsOfWriter(resultSet.getLong("id"))
        );
    }

}
