/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao.store;

import com.toystore.model.store.Age;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class AgeDAO extends BaseDAO<Age,Integer>{

    @Override
    public Age mapResultSetToObject(ResultSet rs) throws SQLException {
        return new Age(rs.getInt("AgeID"), rs.getString("AgeRange"));
    }

    @Override
    public String getTableName() {
       return "Age";
    }

    @Override
    public String getPrimaryKeyColumn() {
          return "AgeID";
    }
    
    public boolean insertAge(Age Age) {
        String query = "INSERT INTO Age (AgeRange) VALUES (?, ?, ?)";
        return insert(query, Age.getAgeRange());
    }
    
    public boolean updateAge(Age Age) {
        String query = "UPDATE Age SET AgeRange=? WHERE AgeId=?";
        return update(query, Age.getAgeRange(), Age.getAgeId());
    }
    
    public boolean deleteAge(int AgeId) {
        return delete(AgeId);
    }
    
    public Age getAgeById(int AgeId) {
        return findById(AgeId);
    }
    
    public List<Age> getAllAges() {
        return findAll();
    }
    
}
