package com.ezt.eztproject.bean;

import java.io.Serializable;

/**
 * Created by SEELE on 2017/8/12.
 */

public class GasStationInfo implements Serializable{
    private String name;
    private String tel;
    private String address;
    private String position;
    private String distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GasStationInfo{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", position='" + position + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
