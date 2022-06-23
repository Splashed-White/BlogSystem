package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created with IntelliJ IDEA.
 * Description: 封装数据库操作
 * User: X2148
 * Date: 2022-06-15
 * Time: 21:48
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/BlogSystem?characterEncoding=utf8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static DataSource dataSource = null;
    // 1. 创建数据源
    public static DataSource getDataSource(){
        MysqlDataSource mysqldataSource = new MysqlDataSource();
        mysqldataSource.setUrl(URL);
        mysqldataSource.setUser(USERNAME);
        mysqldataSource.setPassword(PASSWORD);
        dataSource = mysqldataSource;
        return dataSource;
    }
    // 2. 数据库连接
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
    // 3. 释放资源
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
