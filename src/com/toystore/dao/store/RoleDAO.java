/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao.store;

/**
 *
 * @author Asus
 */
import com.toystore.model.store.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleDAO extends BaseDAO<Role, Integer> {
    @Override
    public String getTableName() {
        return "role";
    }

    @Override
    public String getPrimaryKeyColumn() {
        return "roleId";
    }

    @Override
    public Role mapResultSetToObject(ResultSet rs) throws SQLException {
        return new Role(
            rs.getInt("roleId"),
            rs.getString("roleName"),
            rs.getString("description")
        );
    }

    public boolean insertRole(Role role) {
        String query = "INSERT INTO role (roleName, description) VALUES (?, ?)";
        return insert(query, role.getRoleName(), role.getDescription());
    }

    public boolean updateRole(Role role) {
        String query = "UPDATE role SET roleName=?, description=? WHERE roleId=?";
        return update(query, role.getRoleName(), role.getDescription(), role.getRoleId());
    }

    public boolean deleteRole(int roleId) {
        return delete(roleId);
    }

    public Role getRoleById(int roleId) {
        return findById(roleId);
    }

    public List<Role> getAllRoles() {
        return findAll();
    }
}

