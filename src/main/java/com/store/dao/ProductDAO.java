package com.store.dao;
import com.store.model.Product;

import java.sql.*;
import java.util.*;
public class ProductDAO extends BaseDAO{
    public ProductDAO(){
        super();
    }
    public List<Product> searchProductByName(String keyword,String sortBy) {
        List<Product> products = new ArrayList<>();
        String orderByClause;
        if (sortBy == null) {
            sortBy = "az";
        }
        switch (sortBy) {
            case "za":
                orderByClause = " ORDER BY productName DESC";
                break;
            case "price_asc":
                orderByClause = " ORDER BY salePrice ASC";
                break;
            case "price_desc":
                orderByClause = " ORDER BY salePrice DESC";
                break;
            case "az":
            default:
                orderByClause = " ORDER BY productName ASC";
                break;
        }
        String query = "SELECT id, productName, retailPrice, salePrice, stockQuantity, thumbnailImageUrl "
                + "FROM tblProduct "
                + "WHERE productName ILIKE ? "+orderByClause;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                                       product.setId(rs.getInt("id"));
                    product.setProductName(rs.getString("productName"));
                    product.setRetailPrice(rs.getDouble("retailPrice"));
                    product.setSalePrice(rs.getDouble("salePrice"));
                    product.setStockQuantity(rs.getInt("stockQuantity"));
                    product.setThumbnailImageUrl(rs.getString("thumbnailImageUrl"));
                    products.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    public Product getProductById(int productId) {
        Product product = null;
        String query = "SELECT * FROM tblProduct WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setProductName(rs.getString("productName"));
                    product.setDetailedDescription(rs.getString("detailedDescription"));
                    product.setImportPrice(rs.getDouble("importPrice"));
                    product.setRetailPrice(rs.getDouble("retailPrice"));
                    product.setSalePrice(rs.getDouble("salePrice"));
                    product.setStockQuantity(rs.getInt("stockQuantity"));
                    product.setReservedQuantity(rs.getInt("reservedQuantity"));
                    product.setMinReorderLevel(rs.getInt("minReorderLevel"));
                    product.setUnit(rs.getString("unit"));
                    product.setThumbnailImageUrl(rs.getString("thumbnailImageUrl"));
                    product.setImageGalleryUrls(rs.getString("imageGalleryUrls"));
                    product.setProductStatus(rs.getString("productStatus"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}
