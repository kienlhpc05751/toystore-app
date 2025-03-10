/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao.store;

import com.toystore.model.store.Brand;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class BrandDAO extends BaseDAO<Brand, Integer> {
    @Override
    public String getTableName() {
        return "brand";
    }

    @Override
    public String getPrimaryKeyColumn() {
        return "brandId";
    }

    @Override
    public Brand mapResultSetToObject(ResultSet rs) throws SQLException {
        return new Brand(
            rs.getInt("brandId"),
            rs.getString("name"),
            rs.getString("OriginBrand"),
            rs.getString("Description")
        );
    }
    
    public boolean insertBrand(Brand brand) {
        String query = "INSERT INTO brand (name, description, originBrand) VALUES (?, ?, ?)";
        return insert(query, brand.getName(), brand.getDescription(), brand.getOriginBrand());
    }
    
    public boolean updateBrand(Brand brand) {
        String query = "UPDATE brand SET name=?, description=?, OriginBrand=? WHERE brandId=?";
        return update(query, brand.getName(), brand.getDescription(), brand.getOriginBrand(), brand.getBrandId());
    }
    
    public boolean deleteBrand(int brandId) {
        return delete(brandId);
    }
    
    public Brand getBrandById(int brandId) {
        return findById(brandId);
    }
    
    public List<Brand> getAllBrands() {
        return findAll();
    }
}

