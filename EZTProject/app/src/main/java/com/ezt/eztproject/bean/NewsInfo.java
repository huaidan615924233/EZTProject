package com.ezt.eztproject.bean;

import java.io.Serializable;

/**
 * Created by Sunshine on 2017/8/17.
 */

public class NewsInfo implements Serializable {
    private String newsIcon;
    private String newsTitle;
    private String newsData;

    public String getNewsIcon() {
        return newsIcon;
    }

    public void setNewsIcon(String newsIcon) {
        this.newsIcon = newsIcon;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsData() {
        return newsData;
    }

    public void setNewsData(String newsData) {
        this.newsData = newsData;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "newsIcon='" + newsIcon + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsData='" + newsData + '\'' +
                '}';
    }
}
