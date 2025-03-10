/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.model.store;

/**
 *
 * @author Asus
 */
public class product {
    private int productId;
    private int categoryId;
    private int brandId;
    private int ageId;
    private int materialId;
    private String name;
    private double price;
    private double originalPrice;
    private String createdAt;
    private boolean status;
    private String description;
    private boolean sex; // true = Nam, false = Nữ
    private String image;
    private int quantity;
    private String barcode;
    private String urlBarcode;

//    public product(int aInt, String string, String string1, double aDouble) {
//    }

    public product() {
    }

    
    public product(int productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    

    public product(int productId, int categoryId, int brandId, int ageId, int materialId, String name, double price, double originalPrice, String createdAt, boolean status, String description, boolean sex, String image, int quantity, String barcode, String urlBarcode) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.ageId = ageId;
        this.materialId = materialId;
        this.name = name;
        this.price = price;
        this.originalPrice = originalPrice;
        this.createdAt = createdAt;
        this.status = status;
        this.description = description;
        this.sex = sex;
        this.image = image;
        this.quantity = quantity;
        this.barcode = barcode;
        this.urlBarcode = urlBarcode;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getAgeId() {
        return ageId;
    }

    public void setAgeId(int ageId) {
        this.ageId = ageId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUrlBarcode() {
        return urlBarcode;
    }

    public void setUrlBarcode(String urlBarcode) {
        this.urlBarcode = urlBarcode;
    }

    @Override
    public String toString() {
        return "product{" + "productId=" + productId + ", categoryId=" + categoryId + ", brandId=" + brandId + ", ageId=" + ageId + ", materialId=" + materialId + ", name=" + name + ", price=" + price + ", originalPrice=" + originalPrice + ", createdAt=" + createdAt + ", status=" + status + ", description=" + description + ", sex=" + sex + ", image=" + image + ", quantity=" + quantity + ", barcode=" + barcode + ", urlBarcode=" + urlBarcode + '}';
    }
    
    
}
