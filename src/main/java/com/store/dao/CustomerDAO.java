package com.store.dao;

import com.store.model.Customer;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends BaseDAO {

    public CustomerDAO() {
        super();
    }
    public List<Customer> getCustomerRevenueStats(Date startDate, Date endDate) {
        List<Customer> customerStatsList = new ArrayList<>();
        // Sử dụng CTE (OrderTotals) để tính tổng tiền từng đơn hàng trước,
        String query = "WITH OrderTotals AS ( "
                + "    SELECT o.id AS orderId, "
                + "           o.customerId, "
                + "           COALESCE(SUM(od.quantity * od.unitPrice), 0) "
                + "           + COALESCE(o.shippingFee, 0) - COALESCE(o.discountAmount, 0) AS orderTotal "
                + "    FROM tblOrder o "
                + "    LEFT JOIN tblOrderDetail od ON o.id = od.orderId "
                + "    WHERE o.orderStatus = 'Hoàn thành' "
                + "      AND DATE(o.orderDate) BETWEEN ? AND ? "
                + "    GROUP BY o.id, o.customerId, o.shippingFee, o.discountAmount "
                + ") "
                + "SELECT c.id, c.fullName, c.phoneNumber, c.loyaltyPoints, c.membershipTier, "
                + "       SUM(ot.orderTotal) AS calculatedRevenue "
                + "FROM tblCustomer c "
                + "JOIN OrderTotals ot ON c.id = ot.customerId "
                + "GROUP BY c.id, c.fullName, c.phoneNumber, c.loyaltyPoints, c.membershipTier "
                + "ORDER BY calculatedRevenue DESC";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setId(rs.getInt("id"));
                    customer.setFullName(rs.getString("fullName"));
                    customer.setPhoneNumber(rs.getString("phoneNumber"));
                    customer.setLoyaltyPoints(rs.getInt("loyaltyPoints"));
                    customer.setMembershipTier(rs.getString("membershipTier"));
                    customer.setCalculatedRevenue(rs.getDouble("calculatedRevenue"));

                    customerStatsList.add(customer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerStatsList;
    }
}