package com.example.sustainability.api.api;

public class ActionSummary {

    private boolean recorded;
    private int points;

    public ActionSummary() {
        this.recorded = false;
    }

    public ActionSummary(boolean isRecorded, int points) {
        this.recorded = isRecorded;
        this.points = points;
    }

    public boolean isRecorded() {
        return recorded;
    }

    public void setRecorded(boolean recorded) {
        this.recorded = recorded;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
