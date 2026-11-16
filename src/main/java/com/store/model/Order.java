package com.store.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int id;
    private Date orderDate;
    private String recipientName;
    private String recipientPhone;
    private String shippingAddress;
    private double subtotalAmount;
    private double shippingFee;
    private double discountAmount;
    private double totalAmountPayable;
    private String paymentMethod;
    private String paymentStatus;
    private String orderStatus;
    private String customerNote;
    //  tham chiếu ngược về Customer
    private Customer customer;
    public Order() {
    }
    public Order(int id, Date orderDate, String recipientName, String recipientPhone,
                 String shippingAddress, double shippingFee, double discountAmount,
                 double totalAmountPayable, String paymentMethod, String paymentStatus,
                 String orderStatus, String customerNote) {
        this.id = id;
        this.orderDate = orderDate;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.shippingAddress = shippingAddress;
        this.shippingFee = shippingFee;
        this.discountAmount = discountAmount;
        this.totalAmountPayable = totalAmountPayable;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
        this.customerNote = customerNote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public double getSubtotalAmount() {
        return subtotalAmount;
    }

    public void setSubtotalAmount(double subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getTotalAmountPayable() {
        return totalAmountPayable;
    }

    public void setTotalAmountPayable(double totalAmountPayable) {
        this.totalAmountPayable = totalAmountPayable;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}