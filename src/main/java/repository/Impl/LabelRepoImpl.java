package repository.Impl;

import lombok.SneakyThrows;
import model.Label;
import repository.LabelRepository;
import util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LabelRepoImpl implements LabelRepository {

    @Override
    @SneakyThrows(SQLException.class)
    public Label getById(Long id) {
        Label returnedLabel = new Label();
        String sql = "select id, name from labels where id = ?";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            returnedLabel.setId(result.getLong("id"));
            returnedLabel.setName(result.getString("name"));
        }

        return returnedLabel;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public List<Label> getAll() {
        List<Label> labels = new ArrayList<>();
        String sql = "select * from labels";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                labels.add(new Label(resultSet.getLong("id"),
                        resultSet.getString("name")));
            }
        }

        return labels;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public int save(Label label, Long postIdId) {
        String sql = "insert into labels (post_id, name) values (?, ?)";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, postIdId);
            statement.setString(2, label.getName());
            return statement.executeUpdate();
        }
    }


    @Override
    @SneakyThrows(SQLException.class)
    public int update(Label newLabel, Long id)  {
        String sql = "update labels set name = ? where id = ?";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newLabel.getName());
            statement.setLong(2, id);
            return statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows(SQLException.class)
    public int delete(Long id) {
        String sql = "delete from labels where id = ?";

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }
}
