/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao;

/**
 *
 * @author Asus
 */
import com.toystore.model.Roles;
import com.toystore.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO<User> {

    public UserDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    public User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            User user = new User();
//            user.setUserId(rs.getInt("user_id"));
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            long roleId = rs.getLong("role_id");
            // Truy vấn lấy Role tương ứng
            Roles role = getRoleById(roleId);
            user.setRole(role);
//            user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return user;
        }
        return null;
    }

    public User login(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
//        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            long roleId = rs.getLong("role_id");

            // Truy vấn lấy Role tương ứng
            Roles role = getRoleById(roleId);
            user.setRole(role);
//            user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return user;
        }
        return null;
    }

    @Override
    public boolean add(User user) throws SQLException {
        String sql = "INSERT INTO User (username, password, email, role_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, (int) user.getRole().getId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql = "UPDATE User SET username = ?, password = ?, email = ?, role_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, (int) user.getRole().getId());
            stmt.setInt(5, (int) user.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM User WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public User getById(int id) throws SQLException {
        String sql = "SELECT * FROM User WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Lấy thông tin role_id
                long roleId = rs.getLong("role_id");

                // Truy vấn lấy Role tương ứng
                Roles role = getRoleById(roleId);
                return new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("password"), rs.getString("email"), role);
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "select * from user";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Lấy thông tin role_id
                long roleId = rs.getLong("role_id");

                // Truy vấn lấy Role tương ứng
                Roles role = getRoleById(roleId);

                // Thêm đối tượng User vào danh sách
                users.add(new User(rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        role));
            }
        }
        return users;
    }

// Phương thức bổ trợ để lấy Role từ role_id
    private Roles getRoleById(long roleId) throws SQLException {
        String sql = "SELECT * FROM Role WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, roleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Roles((int) rs.getLong("id"), rs.getString("name"));
            }
        }
        return null;
    }

//    public List<Role> getAllRoles() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
