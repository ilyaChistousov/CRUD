package util;


import lombok.SneakyThrows;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionUtil {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    @SneakyThrows(SQLException.class)
    public static Connection getConnection() {
        return DriverManager.getConnection(
                PropertiesUtil.getProperties(URL_KEY),
                PropertiesUtil.getProperties(USERNAME_KEY),
                PropertiesUtil.getProperties(PASSWORD_KEY));
    }

}
