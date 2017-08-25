package com.task.basilischi.funjebret.models;

/**
 * Created by ajilantang on 25/08/17.
 */

public class Matches {
    private String home, away, date, leagueName;
    public Matches() {
    }

    public Matches(String home, String away, String date, String leagueName) {
        this.home = home;
        this.away = away;
        this.date = date;
        this.leagueName = leagueName;

    }

    public String getLeagueName() { return leagueName; }

    public void setLeagueName(String leagueName) { this.leagueName = leagueName; }


    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }
}
