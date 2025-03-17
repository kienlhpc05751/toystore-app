package com.toystore.dao.store;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Asus
 */
import com.toystore.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseDAO<T, K> {

    protected Connection connection;
    private static final Logger LOGGER = Logger.getLogger(BaseDAO.class.getName());

    public BaseDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Database connection error: {0}", e.getMessage());
        }
    }

    public abstract T mapResultSetToObject(ResultSet rs) throws SQLException;

    public abstract String getTableName();

    public abstract String getPrimaryKeyColumn();

    public abstract List<String> validColumns();

    public boolean insert(String query, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParameters(stmt, params);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Insert error: {0}", e.getMessage());
            return false;
        }
    }

    public boolean update(String query, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParameters(stmt, params);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Update error: {0}", e.getMessage());
            return false;
        }
    }

    public boolean delete(K id) {
        String query = "DELETE FROM " + getTableName() + " WHERE " + getPrimaryKeyColumn() + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Delete error: {0}", e.getMessage());
            return false;
        }
    }

    public T findById(K id) {
        String query = "SELECT * FROM " + getTableName() + " WHERE " + getPrimaryKeyColumn() + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToObject(rs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "FindById error: {0}", e.getMessage());
        }
        return null;
    }

    public List<T> findByColumn(String key, String column) {
        String query = "SELECT * FROM " + getTableName() + " WHERE " + column + " LIKE ?";
        List<T> list = new ArrayList<>();
//        String query = "SELECT * FROM " + getTableName();
        String KEY = " ";
        KEY = key;
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            stmt.setObject(1, "%" + KEY + "%");
            while (rs.next()) {
                list.add(mapResultSetToObject(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "FindAll error: {0}", e.getMessage());
        }
        return list;
    }

    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        String query = "SELECT * FROM " + getTableName();
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToObject(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "FindAll error: {0}", e.getMessage());
        }
        return list;
    }

    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

    public List<T> findByName(String keyword) {
        String query = "SELECT * FROM product WHERE name LIKE ?";
        List<T> productList = new ArrayList<>();
        if (keyword == null) {
            keyword = " ";
        }

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");  // ✅ Gán giá trị cho tham số `?`
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productList.add(mapResultSetToObject(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm sản phẩm: {0}", e.getMessage());
        }
        return productList;
    }

    public List<T> findByOrderID(String keyword) {
        String query = "SELECT * FROM orderdetail WHERE OrderID LIKE ?";
        List<T> productList = new ArrayList<>();
        if (keyword == null) {
            keyword = " ";
        }

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");  // ✅ Gán giá trị cho tham số `?`
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productList.add(mapResultSetToObject(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm sản phẩm: {0}", e.getMessage());
        }
        return productList;
    }

    public T insertAndReturn(String query, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setParameters(stmt, params);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return findById((K) generatedKeys.getObject(1));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Insert error: {0}", e.getMessage());
        }
        return null;
    }

}
