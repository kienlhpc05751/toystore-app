/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao.store;

import com.toystore.model.store.Material;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Asus
 */
public class MaterialDAO extends BaseDAO<Material, Integer> {
    @Override
    public String getTableName() {
        return "material";
    }

    @Override
    public String getPrimaryKeyColumn() {
        return "materialId";
    }

    @Override
    public Material mapResultSetToObject(ResultSet rs) throws SQLException {
        return new Material(
            rs.getInt("materialId"),
            rs.getString("name"),
            rs.getString("description")
        );
    }
    
    public boolean insertMaterial(Material material) {
        String query = "INSERT INTO material (name, description) VALUES (?, ?, ?)";
        return insert(query, material.getName(), material.getDescription());
    }
    
    public boolean updateMaterial(Material material) {
        String query = "UPDATE material SET name=?, description=? WHERE materialId=?";
        return update(query, material.getName(), material.getDescription(), material.getMaterialId());
    }
    
    public boolean deleteMaterial(int materialId) {
        return delete(materialId);
    }
    
    public Material getMaterialById(int materialId) {
        return findById(materialId);
    }
    
    public List<Material> getAllMaterials() {
        return findAll();
    }
}
