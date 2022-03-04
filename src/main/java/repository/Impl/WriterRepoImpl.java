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

    @Override
    @SneakyThrows(SQLException.class)
    public Writer getById(Long id) {
        Writer returnedWriter = new Writer();
        String sql = "select * from writers where id = ?";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            returnedWriter.setId(resultSet.getLong("id"));
            returnedWriter.setFirstName(resultSet.getString("first_name"));
            returnedWriter.setLastName(resultSet.getString("last_name"));
            returnedWriter.setPosts(getPostsOfWriter(id));
        }

        return returnedWriter;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();
        String sql = "select * from writers";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                writers.add(new Writer(resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        getPostsOfWriter(resultSet.getLong("id"))));
            }
        }

        return writers;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public int save(Writer writer) {
        String sql = "insert into writers (first_name, last_name) values (?, ?)";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, writer.getFirstName());
            statement.setString(2, writer.getLastName());
            return statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows(SQLException.class)
    public int update(Writer writer, Long id) {
        String sql = "update writers set first_name = ?, last_name = ? where id = ?";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, writer.getFirstName());
            statement.setString(2, writer.getLastName());
            statement.setLong(3, id);
            return statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows(SQLException.class)
    public int delete(Long id) {
        String sql = "delete from writers where id = ?";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }

    @SneakyThrows(SQLException.class)
    private List<Post> getPostsOfWriter(Long writerId) {
        List<Post> posts = new ArrayList<>();
        String sql = "select * from posts p join writers w on p.writer_id = w.id where w.id = ?";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
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

}
