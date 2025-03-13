/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao.store;

import com.toystore.model.store.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class AccountDAO extends BaseDAO<Account, Integer> {

    @Override
    public String getTableName() {
        return "account";
    }

    @Override
    public String getPrimaryKeyColumn() {
        return "accountId";
    }

    @Override
    public Account mapResultSetToObject(ResultSet rs) throws SQLException {
        return new Account(
                rs.getInt("accountId"),
                rs.getInt("RoleID"),
                rs.getString("username"),
                rs.getString("fullname"),
                rs.getString("PhoneNumber"),
                rs.getString("image"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("address"),
                rs.getBoolean("IsDelete"),
                rs.getDate("birthday")
        );
    }

    public boolean insertAccount(Account account) {
        String query = "INSERT INTO account (username, password, email, roleId, IsDelete, fullname, phoneNumber, image, address, birthday) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(query,
                account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.getRoleId(),
                account.isIsDeleted(),
                account.getFullname(),
                account.getPhoneNumber(),
                account.getImage(),
                account.getAddress(),
                account.getBirthday()
        );
    }

    public boolean updateAccount(Account account) {
        String query = "UPDATE account SET username=?, password=?, email=?, roleId=?, IsDelete=?, fullname=?, phoneNumber=?, image=?, address=?, birthday=? "
                + "WHERE accountId=?";
        return update(query,
                account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.getRoleId(),
                account.isIsDeleted(),
                account.getFullname(),
                account.getPhoneNumber(),
                account.getImage(),
                account.getAddress(),
                account.getBirthday(),
                account.getAccountId()
        );
    }

    public boolean deleteAccount(int accountId) {
        return delete(accountId);
    }

    public Account getAccountById(int accountId) {
        return findById(accountId);
    }

    public List<Account> getAllAccounts() {
        return findAll();
    }
}
