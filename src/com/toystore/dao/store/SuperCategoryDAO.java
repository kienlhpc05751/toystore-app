/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao.store;

import com.toystore.model.store.SuperCategory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class SuperCategoryDAO extends BaseDAO<SuperCategory, Integer> {

    @Override
    public String getTableName() {
        return "supercategory";
    }

    @Override
    public String getPrimaryKeyColumn() {
        return "superCategoryId";
    }

    @Override
    public SuperCategory mapResultSetToObject(ResultSet rs) throws SQLException {
        return new SuperCategory(
                rs.getInt("superCategoryId"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("createdAt")
        );
    }

    public boolean insertSuperCategory(SuperCategory superCategory) {
        String query = "INSERT INTO supercategory (name, description, createdAt) VALUES (?, ?, ?)";
        return insert(query, superCategory.getName(), superCategory.getDescription(), superCategory.getCreatedAt());
    }

    public boolean updateSuperCategory(SuperCategory superCategory) {
        String query = "UPDATE supercategory SET name=?, description=?, createdAt=? WHERE superCategoryId=?";
        return update(query, superCategory.getName(), superCategory.getDescription(), superCategory.getCreatedAt(), superCategory.getSuperCategoryId());
    }

    public boolean deleteSuperCategory(int superCategoryId) {
        return delete(superCategoryId);
    }

    public SuperCategory getSuperCategoryById(int superCategoryId) {
        return findById(superCategoryId);
    }

    public List<SuperCategory> getAllSuperCategories() {
        return findAll();
    }

    @Override
    public List<String> validColumns() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
