package com.akashmjain.ipl;

public class Match {
    private final String id;
    private final String season;
    private final String city;
    private final String date;
    private final String team1;
    private final String team2;
    private final String tossWinner;
    private final String tossDecision;
    private final String result;
    private final String dlApplied;
    private final String winner;
    private final String winByRuns;
    private final String winByWickets;
    private final String playerOfMatch;
    private final String venue;
    private final String umpire1;
    private final String umpire2;
    private final String umpire3;


    public Match(String id, String season, String city, String date, String team1, String team2, String tossWinner, String tossDecision, String result, String dlApplied, String winner, String winByRuns, String winByWickets, String playerOfMatch, String venue, String umpire1, String umpire2, String umpire3) {
        this.id                 = id;
        this.season             = season;
        this.city               = city;
        this.date               = date;
        this.team1              = team1;
        this.team2              = team2;
        this.tossWinner         = tossWinner;
        this.tossDecision       = tossDecision;
        this.result             = result;
        this.dlApplied          = dlApplied;
        this.winner             = winner;
        this.winByRuns          = winByRuns;
        this.winByWickets       = winByWickets;
        this.playerOfMatch      = playerOfMatch;
        this.venue              = venue;
        this.umpire1            = umpire1;
        this.umpire2            = umpire2;
        this.umpire3            = umpire3;
    }

    public String getId() {
        return id;
    }

    public String getSeason() {
        return season;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public String getTossDecision() {
        return tossDecision;
    }

    public String getResult() {
        return result;
    }

    public String getDlApplied() {
        return dlApplied;
    }

    public String getWinner() {
        return winner;
    }

    public String getWinByRuns() {
        return winByRuns;
    }

    public String getWinByWickets() {
        return winByWickets;
    }

    public String getPlayerOfMatch() {
        return playerOfMatch;
    }

    public String getVenue() {
        return venue;
    }

    public String getUmpire1() {
        return umpire1;
    }

    public String getUmpire2() {
        return umpire2;
    }

    public String getUmpire3() {
        return umpire3;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", season='" + season + '\'' +
                ", city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", tossWinner='" + tossWinner + '\'' +
                ", tossDecision='" + tossDecision + '\'' +
                ", result='" + result + '\'' +
                ", dlApplied='" + dlApplied + '\'' +
                ", winner='" + winner + '\'' +
                ", winByRuns='" + winByRuns + '\'' +
                ", winByWickets='" + winByWickets + '\'' +
                ", playerOfMatch='" + playerOfMatch + '\'' +
                ", venue='" + venue + '\'' +
                ", umpire1='" + umpire1 + '\'' +
                ", umpire2='" + umpire2 + '\'' +
                ", umpire3='" + umpire3 + '\'' +
                '}';
    }
}
