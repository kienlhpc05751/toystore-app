package com.toystore.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Asus
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.toystore.db.DatabaseConnection;

// BaseDAO - Generic CRUD operations
public abstract class BaseDAO<T> {
    protected Connection connection;
    
    public BaseDAO() throws SQLException, ClassNotFoundException {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public abstract boolean add(T entity) throws SQLException;
    public abstract boolean update(T entity) throws SQLException;
    public abstract boolean delete(int id) throws SQLException;
    public abstract T getById(int id) throws SQLException;
    public abstract List<T> getAll() throws SQLException;
} 