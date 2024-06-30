package com.web.jsp_coffee.model;

import com.web.jsp_coffee.dao.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserModel implements CommonDao <User>{
    private Connection connection;

    public UserModel() throws SQLException {
         connection = DBUtil.getConnection();
    }

    public static UserModel getInstance() throws SQLException {
        return new UserModel();
    }

    // close connection
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean insert(User T) {
        // insert user to database
        try {
            String query = String.format("INSERT INTO users (Name, DateOfBirth, Phone, Email, Password, Address, Role) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    T.getName(), T.getDateOfBirth(), T.getPhone(), T.getEmail(), T.getPassword(), T.getAddress(), T.getRole());
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query) > 0;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User T) {
         try {
                String query = String.format("UPDATE users SET Name = '%s', DateOfBirth = '%s', Phone = '%s', Email = '%s', Password = '%s', Address = '%s', Role = '%s' WHERE UserID = '%d'",
                      T.getName(), T.getDateOfBirth(), T.getPhone(), T.getEmail(), T.getPassword(), T.getAddress(), T.getRole(), T.getUserID());
                Statement statement = connection.createStatement();
                return  statement.executeUpdate(query)  > 0;
         }catch (Exception ex) {
              ex.printStackTrace();
              return false;
         }
    }



    @Override
    public boolean delete(int id) {
        try {
            String query = String.format("DELETE FROM users WHERE UserID = %d", id);
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query) > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User selectById(String id) {
        User user = new User();
        String query = String.format("SELECT * FROM users WHERE UserID = %s", id);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                user.setUserID(resultSet.getInt("UserID"));
                user.setName(resultSet.getString("Name"));
                user.setDateOfBirth(resultSet.getString("DateOfBirth"));
                user.setPhone(resultSet.getString("Phone"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
                user.setAddress(resultSet.getString("Address"));
                user.setRole(resultSet.getString("Role"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<User> selectAll() {
        ArrayList<User> listUser = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt("UserID"));
                user.setName(resultSet.getString("Name"));
                user.setDateOfBirth(resultSet.getString("DateOfBirth"));
                user.setPhone(resultSet.getString("Phone"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
                user.setAddress(resultSet.getString("Address"));
                user.setRole(resultSet.getString("Role"));
                listUser.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listUser;
    }
}
