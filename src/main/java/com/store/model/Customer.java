package com.store.model;

import java.util.Date;
import java.util.List;

public class Customer extends User {
    private String defaultDeliveryAddress;
    private Date dateOfBirth;
    private String gender;
    private int loyaltyPoints;
    private String membershipTier;
    private String accountStatus;
    private Date registrationDate;

    // quan hệ 1-n với Order
    private List<Order> orders;

    // Thuộc tính dẫn xuất dùng riêng cho module thống kê doanh thu
    private double calculatedRevenue;
    public Customer() {
        super();
    }
    public Customer(int id, String fullName, String phoneNumber, String password,
                    String defaultDeliveryAddress, Date dateOfBirth, String gender,
                    int loyaltyPoints, String membershipTier, String accountStatus,
                    Date registrationDate) {
        super(id, fullName, phoneNumber, password);
        this.defaultDeliveryAddress = defaultDeliveryAddress;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.loyaltyPoints = loyaltyPoints;
        this.membershipTier = membershipTier;
        this.accountStatus = accountStatus;
        this.registrationDate = registrationDate;
    }

    public String getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(String defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getMembershipTier() {
        return membershipTier;
    }

    public void setMembershipTier(String membershipTier) {
        this.membershipTier = membershipTier;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public double getCalculatedRevenue() {
        return calculatedRevenue;
    }

    public void setCalculatedRevenue(double calculatedRevenue) {
        this.calculatedRevenue = calculatedRevenue;
    }
}