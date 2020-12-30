package com.akashmjain.ipl;

public class Match {
    private final String id;
    private final String season;
    private final String city;
    private final String date;
    private final String team1;
    private final String team2;
    private final String toss_winner;
    private final String toss_decision;
    private final String result;
    private final String dl_applied;
    private final String winner;
    private final String win_by_runs;
    private final String win_by_wickets;
    private final String player_of_match;
    private final String venue;
    private final String umpire1;
    private final String umpire2;
    private final String umpire3;


    public Match(String id, String season, String city, String date, String team1, String team2, String toss_winner, String toss_decision, String result, String dl_applied, String winner, String win_by_runs, String win_by_wickets, String player_of_match, String venue, String umpire1, String umpire2, String umpire3) {
        this.id                 = id;
        this.season             = season;
        this.city               = city;
        this.date               = date;
        this.team1              = team1;
        this.team2              = team2;
        this.toss_winner        = toss_winner;
        this.toss_decision      = toss_decision;
        this.result             = result;
        this.dl_applied         = dl_applied;
        this.winner             = winner;
        this.win_by_runs        = win_by_runs;
        this.win_by_wickets     = win_by_wickets;
        this.player_of_match    = player_of_match;
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

    public String getToss_winner() {
        return toss_winner;
    }

    public String getToss_decision() {
        return toss_decision;
    }

    public String getResult() {
        return result;
    }

    public String getDl_applied() {
        return dl_applied;
    }

    public String getWinner() {
        return winner;
    }

    public String getWin_by_runs() {
        return win_by_runs;
    }

    public String getWin_by_wickets() {
        return win_by_wickets;
    }

    public String getPlayer_of_match() {
        return player_of_match;
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
                ", toss_winner='" + toss_winner + '\'' +
                ", toss_decision='" + toss_decision + '\'' +
                ", result='" + result + '\'' +
                ", dl_applied='" + dl_applied + '\'' +
                ", winner='" + winner + '\'' +
                ", win_by_runs='" + win_by_runs + '\'' +
                ", win_by_wickets='" + win_by_wickets + '\'' +
                ", player_of_match='" + player_of_match + '\'' +
                ", venue='" + venue + '\'' +
                ", umpire1='" + umpire1 + '\'' +
                ", umpire2='" + umpire2 + '\'' +
                ", umpire3='" + umpire3 + '\'' +
                '}';
    }
}
