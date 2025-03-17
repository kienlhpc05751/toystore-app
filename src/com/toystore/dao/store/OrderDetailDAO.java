/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao.store;

import com.mysql.cj.protocol.Resultset;
import com.toystore.model.store.OrderDetail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class OrderDetailDAO extends BaseDAO<OrderDetail, Integer> {

    @Override
    public String getTableName() {
        return "orderdetail";
    }

    @Override
    public String getPrimaryKeyColumn() {
        return "orderDetailId";
    }

    @Override
    public OrderDetail mapResultSetToObject(ResultSet rs) throws SQLException {
        return new OrderDetail(
                rs.getInt("orderDetailId"),
                rs.getInt("orderId"),
                rs.getInt("productId"),
                rs.getInt("quantity"),
                rs.getDouble("unitPrice"),
                rs.getDouble("discount")
        );
    }

    public boolean insertOrderDetail(OrderDetail orderDetail) {
        String query = "INSERT INTO orderdetail (orderId, productId, quantity, unitPrice, discount) VALUES (?, ?, ?, ?, ?)";
        return insert(query, orderDetail.getOrderId(), orderDetail.getProductId(), orderDetail.getQuantity(), orderDetail.getUnitPrice(), orderDetail.getDiscount());
    }

    public boolean updateOrderDetail(OrderDetail orderDetail) {
        String query = "UPDATE orderdetail SET orderId=?, productId=?, quantity=?, unitPrice=?, discount=? WHERE orderDetailId=?";
        return update(query, orderDetail.getOrderId(), orderDetail.getProductId(), orderDetail.getQuantity(), orderDetail.getUnitPrice(), orderDetail.getDiscount(), orderDetail.getOrderDetailId());
    }

    public boolean deleteOrderDetail(int orderDetailId) {
        return delete(orderDetailId);
    }

    public OrderDetail getOrderDetailById(int orderDetailId) {
        return findById(orderDetailId);
    }

    public List<OrderDetail> getAllOrderDetails() {
        return findAll();
    }

    public List<OrderDetail> getProductbyOrderID(String keywrod) {
        return findByOrderID(keywrod);
    }

    @Override
    public List<String> validColumns() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
