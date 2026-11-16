package com.store.controller;

import com.store.dao.CustomerDAO;
import com.store.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "CustomerStatsController", urlPatterns = {"/quanli/customer-stats"})
public class CustomerStatsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");

        // Nếu mới vào menu thống kê (chưa có tham số ngày), hiển thị form trống
        if (startDateParam == null || endDateParam == null
                || startDateParam.trim().isEmpty() || endDateParam.trim().isEmpty()) {
            request.getRequestDispatcher("/quanli/customer_stats.jsp").forward(request, response);
            return;
        }

        // Trường hợp quay lại từ trang chi tiết kèm tham số lọc trên URL
        doGetRevenueStats(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGetRevenueStats(request, response);
    }

    // Phương thức xử lý chính khớp với biểu đồ lớp thiết kế
    private void doGetRevenueStats(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        // Giữ lại giá trị ngày đã chọn trên giao diện
        request.setAttribute("inStartDate", startDateStr);
        request.setAttribute("inEndDate", endDateStr);

        if (startDateStr == null || endDateStr == null
                || startDateStr.trim().isEmpty() || endDateStr.trim().isEmpty()) {
            request.setAttribute("outErrorMessage", "Vui lòng chọn đầy đủ ngày bắt đầu và ngày kết thúc!");
            request.getRequestDispatcher("/quanli/customer_stats.jsp").forward(request, response);
            return;
        }

        CustomerDAO customerDAO = null;
        try {
            // Chuyển đổi chuỗi yyyy-MM-dd từ <input type="date"> sang java.sql.Date
            Date startDate = Date.valueOf(startDateStr.trim());
            Date endDate = Date.valueOf(endDateStr.trim());

            // Kiểm tra ràng buộc logic thời gian
            if (startDate.after(endDate)) {
                request.setAttribute("outErrorMessage", "Ngày bắt đầu không được lớn hơn ngày kết thúc!");
                request.getRequestDispatcher("/quanli/customer_stats.jsp").forward(request, response);
                return;
            }

            // Truy xuất thống kê từ CSDL
            customerDAO = new CustomerDAO();
            List<Customer> statsList = customerDAO.getCustomerRevenueStats(startDate, endDate);

            request.setAttribute("outStatsList", statsList);

            if (statsList == null || statsList.isEmpty()) {
                request.setAttribute("outErrorMessage", "Không có dữ liệu doanh thu khách hàng trong khoảng thời gian này.");
            }

        } catch (IllegalArgumentException e) {
            request.setAttribute("outErrorMessage", "Định dạng ngày tháng không hợp lệ!");
        } finally {
            if (customerDAO != null) {
                customerDAO.closeConnection();
            }
        }

        request.getRequestDispatcher("/quanli/customer_stats.jsp").forward(request, response);
    }
}