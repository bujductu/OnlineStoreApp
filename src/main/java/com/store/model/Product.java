package com.store.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;

    private String productName;
    private String detailedDescription;
    private double importPrice;
    private double retailPrice;
    private double salePrice;
    private int stockQuantity;
    private int reservedQuantity;
    private int minReorderLevel;
    private String unit;
    private String thumbnailImageUrl;
    private String imageGalleryUrls;
    private String productStatus;
    public Product() {
    }
    public Product(int id, String productName, String detailedDescription, double retailPrice, int stockQuantity, double salePrice, String thumbnailImageUrl) {
        this.id = id;
        this.productName = productName;
        this.detailedDescription = detailedDescription;
        this.retailPrice = retailPrice;
        this.stockQuantity = stockQuantity;
        this.salePrice = salePrice;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public int getMinReorderLevel() {
        return minReorderLevel;
    }

    public void setMinReorderLevel(int minReorderLevel) {
        this.minReorderLevel = minReorderLevel;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getImageGalleryUrls() {
        return imageGalleryUrls;
    }

    public void setImageGalleryUrls(String imageGalleryUrls) {
        this.imageGalleryUrls = imageGalleryUrls;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}
