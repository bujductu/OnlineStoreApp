package com.store.controller;

import com.store.dao.OrderDAO;
import com.store.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "CustomerTransactionsController", urlPatterns = {"/quanli/customer-transactions"})
public class CustomerTransactionsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGetCustomerTransactions(request, response);
    }

    // Phương thức xử lý chính khớp với biểu đồ lớp thiết kế
    private void doGetCustomerTransactions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerIdStr = request.getParameter("customerId");
        String customerName = request.getParameter("customerName");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        // Kiểm tra tham số bắt buộc
        if (customerIdStr == null || startDateStr == null || endDateStr == null
                || customerIdStr.isEmpty() || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/quanli/customer-stats");
            return;
        }

        OrderDAO orderDAO = null;
        try {
            int customerId = Integer.parseInt(customerIdStr.trim());
            Date startDate = Date.valueOf(startDateStr.trim());
            Date endDate = Date.valueOf(endDateStr.trim());

            // Truy xuất danh sách giao dịch của khách hàng
            orderDAO = new OrderDAO();
            List<Order> orderList = orderDAO.getTransactionsByCustomer(customerId, startDate, endDate);

            // Đẩy dữ liệu hiển thị và các tham số ẩn để hỗ trợ nút Quay lại
            request.setAttribute("inCustomerId", customerId);
            request.setAttribute("inStartDate", startDateStr);
            request.setAttribute("inEndDate", endDateStr);
            request.setAttribute("outCustomerName",
                    (customerName != null && !customerName.trim().isEmpty()) ? customerName : "Khách hàng #" + customerId);
            request.setAttribute("outOrderList", orderList);

            request.getRequestDispatcher("/quanli/customer_transactions.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            // Bao gồm cả NumberFormatException và lỗi sai định dạng Date
            response.sendRedirect(request.getContextPath() + "/quanli/customer-stats");
        } finally {
            if (orderDAO != null) {
                orderDAO.closeConnection();
            }
        }
    }
}