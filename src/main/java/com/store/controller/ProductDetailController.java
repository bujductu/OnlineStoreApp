package com.store.controller;

import com.store.dao.ProductDAO;
import com.store.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Khai báo đường dẫn URL cho chức năng xem chi tiết
@WebServlet(name = "ProductDetailController", urlPatterns = {"/khachhang/detail"})
public class ProductDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy mã sản phẩm (inProductId) từ URL (Ví dụ: /khachhang/detail?id=5)
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int productId = Integer.parseInt(idParam);

                // Truy xuất DAO để lấy chi tiết sản phẩm
                ProductDAO productDAO = new ProductDAO();
                Product product = productDAO.getProductById(productId);

                if (product != null) {
                    // Đẩy đối tượng Product ra View
                    request.setAttribute("outProduct", product);
                    request.getRequestDispatcher("/khachhang/product_detail.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Nếu id không hợp lệ hoặc không tìm thấy sản phẩm, chuyển về trang tìm kiếm
        response.sendRedirect(request.getContextPath() + "/khachhang/search");
    }
}