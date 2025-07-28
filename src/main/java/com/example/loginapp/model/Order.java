package com.example.loginapp.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private String orderNo;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    private String sellerPayment;

    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    private String productName;
    private int quantity;
    private String customerDetails;
    private String dispatchTime;
    private String paymentStatus;
    private double totalAmount;
    private double sellerAmount;
    private String referenceNo;

    @Column(name = "seller_payment_status")
    private String sellerPaymentStatus;


    // ✅ Many-to-one relationship to User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ---------- Getters and Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getSellerPayment() {
        return sellerPayment;
    }

    public void setSellerPayment(String sellerPayment) {
        this.sellerPayment = sellerPayment;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(String customerDetails) {
        this.customerDetails = customerDetails;
    }

    public String getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(String dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getSellerAmount() {
        return sellerAmount;
    }

    public void setSellerAmount(double sellerAmount) {
        this.sellerAmount = sellerAmount;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSellerPaymentStatus() {
        return sellerPaymentStatus;
    }

    public void setSellerPaymentStatus(String sellerPaymentStatus) {
        this.sellerPaymentStatus = sellerPaymentStatus;
    }
}


