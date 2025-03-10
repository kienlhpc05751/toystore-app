/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.model.store;

/**
 *
 * @author Asus
 */
public class PaymentMethod {
   
    public int paymentMethodId;
    public String methodName;
    public String createdAt;
    public String description;

    public PaymentMethod() {
    }

    public PaymentMethod(int paymentMethodId, String methodName, String createdAt, String description) {
        this.paymentMethodId = paymentMethodId;
        this.methodName = methodName;
        this.createdAt = createdAt;
        this.description = description;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}

