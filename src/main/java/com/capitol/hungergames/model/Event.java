package com.capitol.hungergames.model;

public class Event {
    private int id;
    private int tributeId;
    private EventType type;
    private int points;
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

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
