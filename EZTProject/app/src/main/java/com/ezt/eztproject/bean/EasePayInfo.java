package com.ezt.eztproject.bean;

import java.io.Serializable;

/**
 * Created by Sunshine on 2017/8/18.
 */

public class EasePayInfo implements Serializable {
    private String state;
    private String title;
    private String desc;
    private String price;
    private String usedData;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUsedData() {
        return usedData;
    }

    public void setUsedData(String usedData) {
        this.usedData = usedData;
    }

    @Override
    public String toString() {
        return "EasePayInfo{" +
                "state='" + state + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", price='" + price + '\'' +
                ", usedData='" + usedData + '\'' +
                '}';
    }
}
