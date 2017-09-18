package com.ezt.eztproject.bean;

import java.io.Serializable;

/**
 * Created by Sunshine on 2017/8/17.
 */

public class IntegerProduct implements Serializable {

    private String productName;
    private String productDesc;
    private String needInteger;
    private String productPrice;
    private String productIcon;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getNeedInteger() {
        return needInteger;
    }

    public void setNeedInteger(String needInteger) {
        this.needInteger = needInteger;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    @Override
    public String toString() {
        return "IntegerProduct{" +
                "productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", needInteger='" + needInteger + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productIcon='" + productIcon + '\'' +
                '}';
    }
}
