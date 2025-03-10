/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao.store;

import com.toystore.model.store.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class CategoryDAO extends BaseDAO<Category, Integer> {

    @Override
    public String getTableName() {
        return "category";
    }

    @Override
    public String getPrimaryKeyColumn() {
        return "categoryId";
    }

    @Override
    public Category mapResultSetToObject(ResultSet rs) throws SQLException {
        return new Category(
                rs.getInt("categoryId"),
                rs.getInt("superCategoryId"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("createdAt")
        );
    }

    public boolean insertCategory(Category category) {
        String query = "INSERT INTO category (superCategoryId, name, description, createdAt) VALUES (?, ?, ?, ?)";
        return insert(query, category.getSuperCategoryId(), category.getName(), category.getDescription(), category.getCreatedAt());
    }

    public boolean updateCategory(Category category) {
        String query = "UPDATE category SET superCategoryId=?, name=?, description=?, createdAt=? WHERE categoryId=?";
        return update(query, category.getSuperCategoryId(), category.getName(), category.getDescription(), category.getCreatedAt(), category.getCategoryId());
    }

    public boolean deleteCategory(int categoryId) {
        return delete(categoryId);
    }

    public Category getCategoryById(int categoryId) {
        return findById(categoryId);
    }

    public List<Category> getALLCategories() {
        return findAll();
    }

}
