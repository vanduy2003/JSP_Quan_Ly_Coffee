package com.web.jsp_coffee.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    private Connection connection;

    public Auth() throws SQLException {
        connection = DBUtil.getConnection();
        testConnection();
    }

    public static Auth getInstance() throws SQLException {
        return new Auth();
    }

    private void testConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } else {
            System.out.println("Kết nối cơ sở dữ liệu thất bại!");
            throw new SQLException("Không thể kết nối đến cơ sở dữ liệu");
        }
    }

    public boolean checkLogin(String email, String password) {
        String query = "SELECT * FROM users WHERE Email = ? AND Password = ?";

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, email);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean registerUser(String username, String email, String phone, String password) {
        String query = "INSERT INTO users (Name, Email, Phone, Password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, phone);
            pst.setString(4, password);

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean emailExists(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePassword(String email, String newPassword) {
        String query = "UPDATE users SET Password = ? WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
