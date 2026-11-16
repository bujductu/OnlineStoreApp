package com.store.controller;

import com.store.dao.ProductDAO;
import com.store.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//URL
@WebServlet(name = "ProductDetailController", urlPatterns = {"/khachhang/detail"})
public class ProductDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int productId = Integer.parseInt(idParam);
                ProductDAO productDAO = new ProductDAO();
                Product product = productDAO.getProductById(productId);
                if (product != null) {
                    request.setAttribute("outProduct", product);
                    request.getRequestDispatcher("/khachhang/product_detail.jsp").forward(request, response);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
              response.sendRedirect(request.getContextPath() + "/khachhang/search");
    }
}