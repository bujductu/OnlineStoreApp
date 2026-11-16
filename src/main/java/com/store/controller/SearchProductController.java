package com.store.controller;

import com.store.dao.ProductDAO;
import com.store.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchProductController", urlPatterns = {"/khachhang/search"})
public class SearchProductController extends HttpServlet {

    // Xử lý khi khách hàng vừa truy cập vào menu Tìm kiếm (chưa nhập gì)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("inSortBy", "az");
        request.getRequestDispatcher("/khachhang/search_product.jsp").forward(request, response);
    }

    // Xử lý khi khách hàng nhập từ khóa và nhấn Submit (Tìm kiếm)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        String sortBy = request.getParameter("sortBy");
        if (keyword == null) {
            keyword = "";
        }
        if (sortBy == null || sortBy.trim().isEmpty()) {
            sortBy = "az";
        }
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.searchProductByName(keyword,sortBy);


        request.setAttribute("outProductList", productList);
        request.setAttribute("inKeyword", keyword);
        request.setAttribute("inSortBy", sortBy);


        if (productList == null || productList.isEmpty()) {
            request.setAttribute("outMessage", "Không tìm thấy sản phẩm nào phù hợp với từ khóa: " + keyword);
        }


        request.getRequestDispatcher("/khachhang/search_product.jsp").forward(request, response);
    }
}