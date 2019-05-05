package com.example.kudaki.model;

public class SettingMenu {
    int icon;
    String title;

    public SettingMenu(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
}
