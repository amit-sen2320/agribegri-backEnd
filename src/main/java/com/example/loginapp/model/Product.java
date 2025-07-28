package com.example.loginapp.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", unique = true)
    private String productId;

    private String status;

    @Column(name = "sell_status")
    private String sellStatus;

    private String code;
    private String name;

    @Column(name = "hsn_code")
    private String hsnCode;

    private String unit;

    @Column(name = "unit_qty")
    private Double unitQty;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "seller_will_get")
    private Double sellerWillGet;

    @Column(name = "courier_charge")
    private Double courierCharge;

    @Column(name = "shipped_by")
    private String shippedBy;

    private String company;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "image_url")
    private String imageUrl;


    // Getters and setters (can be generated using Lombok)

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(String sellStatus) {
        this.sellStatus = sellStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(Double unitQty) {
        this.unitQty = unitQty;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getSellerWillGet() {
        return sellerWillGet;
    }

    public void setSellerWillGet(Double sellerWillGet) {
        this.sellerWillGet = sellerWillGet;
    }

    public Double getCourierCharge() {
        return courierCharge;
    }

    public void setCourierCharge(Double courierCharge) {
        this.courierCharge = courierCharge;
    }

    public String getShippedBy() {
        return shippedBy;
    }

    public void setShippedBy(String shippedBy) {
        this.shippedBy = shippedBy;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

