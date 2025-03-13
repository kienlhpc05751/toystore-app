/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao.store;

import com.mysql.cj.protocol.Resultset;
import com.toystore.model.store.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class OrderDAO extends BaseDAO<Order, Integer> {

    @Override
    public String getTableName() {
        return "orders";
    }

    @Override
    public String getPrimaryKeyColumn() {
        return "orderId";
    }

//    @Override
//    public Order mapResultSetToObject(Resultset rs){
////        return new Order(
////            rs.getInt("orderId"),
////            rs.getInt("userId"),
////            rs.getDouble("totalPrice"),
////            rs.getString("createdAt"),
////            rs.getString("status")
////        );
//        return new Order();
//    }
    public boolean insertOrder(Order order) {
        String query = "INSERT INTO orders (userId, totalPrice, createdAt, status) VALUES (?, ?, ?, ?)";
        return insert(query, order.getAccountId(), order.getTotalAmount(), order.getCreatedAt());
    }

    public boolean updateOrder(Order order) {
        String query = "UPDATE orders SET userId=?, totalPrice=?, createdAt=?, status=? WHERE orderId=?";
        return update(query, order.getAccountId(), order.getTotalAmount(), order.getCreatedAt());
    }

    public boolean deleteOrder(int orderId) {
        return delete(orderId);
    }

    public Order getOrderById(int orderId) {
        return findById(orderId);
    }

    public List<Order> getAllOrders() {
        return findAll();
    }

    @Override
    public Order mapResultSetToObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> validColumns() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
