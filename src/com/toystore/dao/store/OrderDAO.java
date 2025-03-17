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
        return "toyStoreDB.order";
    }

    @Override
    public String getPrimaryKeyColumn() {
        return "orderId";
    }

    @Override
    public Order mapResultSetToObject(ResultSet rs) throws SQLException {
        return new Order(
                rs.getInt("orderId"),
                rs.getInt("accountId"),
                rs.getInt("clientID"),
                rs.getString("orderDate"),
                rs.getDouble("totalAmount"),
                rs.getInt("paymentMethodId"),
                rs.getInt("shippingMethodId"),
                rs.getBoolean("status"),
                rs.getDouble("discount"),
                rs.getInt("vourcherID")
        );
    }

    public Order insertOrder(Order order) {
        String query = "INSERT INTO toyStoreDB.order (accountId,clientID, orderDate, totalAmount, paymentMethodId, shippingMethodId, status, discount, vourcherID) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";
        return insertAndReturn(query, order.getAccountId(), order.getClientID(), order.getOrderDate(), order.getTotalAmount(), order.getPaymentMethodId(), order.getShippingMethodId(), order.isStatus(), order.getDiscount(), order.getVoucherId());
    }

    public boolean updateOrder(Order order) {
        String query = "UPDATE toyStoreDB.order SET accountId=?,clientID=?, orderDate=?, totalAmount=?, paymentMethodId=?, shippingMethodId=?, status=?, discount=?, vourcherID=? WHERE orderId=?";
        return update(query, order.getAccountId(), order.getClientID(), order.getOrderDate(), order.getTotalAmount(), order.getPaymentMethodId(), order.getShippingMethodId(), order.isStatus(), order.getDiscount(), order.getVoucherId(), order.getOrderId());
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
    public List<String> validColumns() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
