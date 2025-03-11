/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao.store;

import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.toystore.model.Roles;
import com.toystore.model.User;
import com.toystore.model.store.product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class productDAO extends BaseDAO<product, Integer> {
    @Override
    public String getTableName() {
        return "product";
    }

    @Override
    public String getPrimaryKeyColumn() {
        return "productId";
    }

    @Override
    public product mapResultSetToObject(ResultSet rs) throws SQLException {
        return new product(
            rs.getInt("productId"),
            rs.getInt("categoryId"),
            rs.getInt("brandId"),
            rs.getInt("ageId"),
            rs.getInt("materialId"),
            rs.getString("name"),
            rs.getDouble("price"),
            rs.getDouble("originalPrice"),
            rs.getString("createdAt"),
            rs.getBoolean("status"),
            rs.getString("description"),
            rs.getBoolean("SexID"),
            rs.getString("image"),
            rs.getInt("quantity"),
            rs.getString("barcode"),
            rs.getString("urlBarcode")
        );
    }

    public boolean insertProduct(product product) {
        String query = "INSERT INTO product (categoryId, brandId, ageId, materialId, name, price, originalPrice, createdAt, status, description, sexId, image, quantity, barcode, urlBarcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(query, product.getCategoryId(), product.getBrandId(), product.getAgeId(), product.getMaterialId(), product.getName(), product.getPrice(), product.getOriginalPrice(), product.getCreatedAt(), product.isStatus(), product.getDescription(), product.isSex(), product.getImage(), product.getQuantity(), product.getBarcode(), product.getUrlBarcode());
    }

    public boolean updateProduct(product product) {
        String query = "UPDATE product SET categoryId=?, brandId=?, ageId=?, materialId=?, name=?, price=?, originalPrice=?, createdAt=?, status=?, description=?, sexId=?, image=?, quantity=?, barcode=?, urlBarcode=? WHERE productId=?";
        return update(query, product.getCategoryId(), product.getBrandId(), product.getAgeId(), product.getMaterialId(), product.getName(), product.getPrice(), product.getOriginalPrice(), product.getCreatedAt(), product.isStatus(), product.getDescription(), product.isSex(), product.getImage(), product.getQuantity(), product.getBarcode(), product.getUrlBarcode(), product.getProductId());
    }

    public boolean deleteProduct(int productId) {
        return delete(productId);
    }

    public product getProductById(int productId) {
        return findById(productId);
    }

    public List<product> getAllProducts() {
        return findAll();
    }
}
