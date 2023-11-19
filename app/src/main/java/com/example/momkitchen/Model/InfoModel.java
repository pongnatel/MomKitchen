package com.example.momkitchen.Model;

public class InfoModel {
    private final String title;
    private final String desc;

    public InfoModel(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

}
