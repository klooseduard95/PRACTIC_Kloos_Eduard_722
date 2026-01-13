package com.capitol.hungergames.model;

public class SponsorGift {
    private int id;
    private int tributeId;
    private String itemName;
    private int value;
    private int day;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTributeId() {
        return tributeId;
    }

    public void setTributeId(int tributeId) {
        this.tributeId = tributeId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
