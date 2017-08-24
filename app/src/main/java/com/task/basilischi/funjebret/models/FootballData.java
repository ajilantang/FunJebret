package com.task.basilischi.funjebret.models;

/**
 * Created by syauqi on 24/08/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FootballData {

    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("league_id")
    @Expose
    private String leagueId;
    @SerializedName("league_name")
    @Expose
    private String leagueName;
    @SerializedName("match_date")
    @Expose
    private String matchDate;
    @SerializedName("match_status")
    @Expose
    private String matchStatus;
    @SerializedName("match_time")
    @Expose
    private String matchTime;
    @SerializedName("match_hometeam_name")
    @Expose
    private String matchHometeamName;
    @SerializedName("match_hometeam_score")
    @Expose
    private String matchHometeamScore;
    @SerializedName("match_awayteam_name")
    @Expose
    private String matchAwayteamName;
    @SerializedName("match_awayteam_score")
    @Expose
    private String matchAwayteamScore;
    @SerializedName("match_hometeam_halftime_score")
    @Expose
    private String matchHometeamHalftimeScore;
    @SerializedName("match_awayteam_halftime_score")
    @Expose
    private String matchAwayteamHalftimeScore;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public String getMatchHometeamName() {
        return matchHometeamName;
    }

    public void setMatchHometeamName(String matchHometeamName) {
        this.matchHometeamName = matchHometeamName;
    }

    public String getMatchHometeamScore() {
        return matchHometeamScore;
    }

    public void setMatchHometeamScore(String matchHometeamScore) {
        this.matchHometeamScore = matchHometeamScore;
    }

    public String getMatchAwayteamName() {
        return matchAwayteamName;
    }

    public void setMatchAwayteamName(String matchAwayteamName) {
        this.matchAwayteamName = matchAwayteamName;
    }

    public String getMatchAwayteamScore() {
        return matchAwayteamScore;
    }

    public void setMatchAwayteamScore(String matchAwayteamScore) {
        this.matchAwayteamScore = matchAwayteamScore;
    }

    public String getMatchHometeamHalftimeScore() {
        return matchHometeamHalftimeScore;
    }

    public void setMatchHometeamHalftimeScore(String matchHometeamHalftimeScore) {
        this.matchHometeamHalftimeScore = matchHometeamHalftimeScore;
    }

    public String getMatchAwayteamHalftimeScore() {
        return matchAwayteamHalftimeScore;
    }

    public void setMatchAwayteamHalftimeScore(String matchAwayteamHalftimeScore) {
        this.matchAwayteamHalftimeScore = matchAwayteamHalftimeScore;
    }
}
