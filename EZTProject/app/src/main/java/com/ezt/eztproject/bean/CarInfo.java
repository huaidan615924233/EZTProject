package com.ezt.eztproject.bean;

import java.io.Serializable;

/**
 * Created by Sunshine on 2017/8/17.
 */

public class CarInfo implements Serializable{
    private String carNumber;
    private String carPlate;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "carNumber='" + carNumber + '\'' +
                ", carPlate='" + carPlate + '\'' +
                '}';
    }
}
