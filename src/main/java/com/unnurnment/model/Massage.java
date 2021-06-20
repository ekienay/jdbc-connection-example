package com.unnurnment.model;

public class Massage {
    public static final String TABLE_NAME = "massage";
    public static final String ID = "id";
    public static final String TEXT = "text";
    public static final String TITLE = "title";
    public static final String THEME_ID = "theme_id";

    private int id;
    private String text;
    private String title;
    private int themeId;

    public Massage() {
    }

    public Massage(int id, String text, String title, int themeId) {
        this.id = id;
        this.text = text;
        this.title = title;
        this.themeId = themeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText() {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }
}
