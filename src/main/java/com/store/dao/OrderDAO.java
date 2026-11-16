package com.store.dao;

import com.store.model.Order;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends BaseDAO {

    public OrderDAO() {
        super();
    }
    public List<Order> getTransactionsByCustomer(int customerId, Date startDate, Date endDate) {
        List<Order> orderList = new ArrayList<>();
        String query = "SELECT o.id, o.orderDate, o.recipientName, o.shippingAddress, "
                + "       o.paymentMethod, o.paymentStatus, o.orderStatus, "
                + "       COALESCE(SUM(od.quantity * od.unitPrice), 0) "
                + "       + COALESCE(o.shippingFee, 0) - COALESCE(o.discountAmount, 0) AS totalAmountPayable "
                + "FROM tblOrder o "
                + "LEFT JOIN tblOrderDetail od ON o.id = od.orderId "
                + "WHERE o.customerId = ? "
                + "  AND DATE(o.orderDate) BETWEEN ? AND ? "
                + "GROUP BY o.id, o.orderDate, o.recipientName, o.shippingAddress, "
                + "         o.paymentMethod, o.paymentStatus, o.orderStatus, "
                + "         o.shippingFee, o.discountAmount "
                + "ORDER BY o.orderDate DESC";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, customerId);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setOrderDate(rs.getTimestamp("orderDate"));
                    order.setRecipientName(rs.getString("recipientName"));
                    order.setShippingAddress(rs.getString("shippingAddress"));
                    order.setPaymentMethod(rs.getString("paymentMethod"));
                    order.setPaymentStatus(rs.getString("paymentStatus"));
                    order.setOrderStatus(rs.getString("orderStatus"));
                    order.setTotalAmountPayable(rs.getDouble("totalAmountPayable"));
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }
}