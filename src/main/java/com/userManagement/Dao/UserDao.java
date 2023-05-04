package com.userManagement.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.userManagement.Model.User;

public class UserDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/usermanagement";
    private String jdbcUserName = "root";
    private String jdbcUserPassword = "";
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private static final String INSERT_USER_SQL = "INSERT INTO users ( name, email, country) VALUES (?,?,?);";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER_SQL = "DELETE FROM `users` WHERE id=?;";
    private static final String SELECT_USER_BY_ID = "SELECT id, name, email, country FROM users WHERE id=?;";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET name=?,email=?,country=? WHERE id=?;";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement st = null;
        Class.forName(jdbcDriver);
        con = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcUserPassword);
        return con;
    }

    public void insertUser(User user) throws SQLException, ClassNotFoundException {
        System.out.println("Inserted");
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(INSERT_USER_SQL);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getCountry());
        preparedStatement.executeUpdate();
    }

    public User selectUser(int id) throws SQLException, ClassNotFoundException {
        User user = null;
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_BY_ID);
        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            String email = rs.getString("email");
            String country = rs.getString("country");
            System.out.println(name+" "+email+" "+country);
            user = new User(id, name, email, country);
        }
        return user;
    }

    public List<User> selectAllUsers() throws SQLException, ClassNotFoundException {
        List<User> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_USERS);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            String email = rs.getString("email");
            String country = rs.getString("country");
            int id = rs.getInt("id");
            list.add(new User(id, name, email, country));
        }
        return list;
    }

    public boolean deleteUser(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(DELETE_USER_SQL);
        preparedStatement.setInt(1, id);
        rowDeleted = preparedStatement.executeUpdate() > 0;
        return rowDeleted;
    }
    public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        boolean rowUpdated;
        Connection con = getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(UPDATE_USER_BY_ID);
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getEmail());
        preparedStatement.setString(3,user.getCountry());
        preparedStatement.setInt(4,user.getId());
        rowUpdated=preparedStatement.executeUpdate()>0;
        return  rowUpdated;
    }
}

