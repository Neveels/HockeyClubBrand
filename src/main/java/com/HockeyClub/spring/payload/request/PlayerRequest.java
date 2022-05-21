package com.HockeyClub.spring.payload.request;

public class PlayerRequest {
    private int goals;
    private int assist;

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }
}
