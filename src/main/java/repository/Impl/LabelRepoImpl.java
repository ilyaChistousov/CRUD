package repository.Impl;

import lombok.SneakyThrows;
import model.Label;
import repository.LabelRepository;
import util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LabelRepoImpl implements LabelRepository {
    private static final String GET_ONE_SQL = "select id, name from labels where id = ?";
    private static final String GET_ALL_SQL = "select * from labels";
    private static final String SAVE_SQL = "insert into labels (name) values (?)";
    private static final String UPDATE_SQL = "update labels set name = ? where id = ?";
    private static final String DELETE_SQL = "delete from labels where id = ?";

    @Override
    @SneakyThrows(SQLException.class)
    public Label getById(Long id) {
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ONE_SQL)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            return buildLabel(result);
        }
    }

    @Override
    @SneakyThrows(SQLException.class)
    public List<Label> getAll() {
        List<Label> labels = new ArrayList<>();

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                labels.add(buildLabel(resultSet));
            }
        }

        return labels;
    }

    @Override
    @SneakyThrows(SQLException.class)
    public int save(Label label) {
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL)) {
            statement.setString(1, label.getName());
            return statement.executeUpdate();
        }
    }


    @Override
    @SneakyThrows(SQLException.class)
    public int update(Label newLabel)  {
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, newLabel.getName());
            statement.setLong(2, newLabel.getId());
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
    private Label buildLabel(ResultSet resultSet) {
        return new Label(
                resultSet.getLong("id"),
                resultSet.getString("name")
        );
    }
}
