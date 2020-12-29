package com.akashmjain.ipl;

public class Delivery {
    private final String match_id;
    private final String inning;
    private final String batting_team;
    private final String bowling_team;
    private final String over;
    private final String ball;
    private final String batsman;
    private final String non_striker;
    private final String bowler;
    private final String is_super_over;
    private final String wide_runs;
    private final String bye_runs;
    private final String legbye_runs;
    private final String noball_runs;
    private final String penalty_runs;
    private final String batsman_runs;
    private final String extra_runs;
    private final String total_runs;
    private final String player_dismissed;
    private final String dismissal_kind;


    public Delivery(String match_id, String inning, String batting_team, String bowling_team, String over, String ball, String batsman, String non_striker, String bowler, String is_super_over, String wide_runs, String bye_runs, String legbye_runs, String noball_runs, String penalty_runs, String batsman_runs, String extra_runs, String total_runs, String player_dismissed, String dismissal_kind) {
        this.match_id = match_id;
        this.inning = inning;
        this.batting_team = batting_team;
        this.bowling_team = bowling_team;
        this.over = over;
        this.ball = ball;
        this.batsman = batsman;
        this.non_striker = non_striker;
        this.bowler = bowler;
        this.is_super_over = is_super_over;
        this.wide_runs = wide_runs;
        this.bye_runs = bye_runs;
        this.legbye_runs = legbye_runs;
        this.noball_runs = noball_runs;
        this.penalty_runs = penalty_runs;
        this.batsman_runs = batsman_runs;
        this.extra_runs = extra_runs;
        this.total_runs = total_runs;
        this.player_dismissed = player_dismissed;
        this.dismissal_kind = dismissal_kind;
    }

    public String getMatch_id() {
        return match_id;
    }

    public String getInning() {
        return inning;
    }

    public String getBatting_team() {
        return batting_team;
    }

    public String getBowling_team() {
        return bowling_team;
    }

    public String getOver() {
        return over;
    }

    public String getBall() {
        return ball;
    }

    public String getBatsman() {
        return batsman;
    }

    public String getNon_striker() {
        return non_striker;
    }

    public String getBowler() {
        return bowler;
    }

    public String getIs_super_over() {
        return is_super_over;
    }

    public String getWide_runs() {
        return wide_runs;
    }

    public String getBye_runs() {
        return bye_runs;
    }

    public String getLegbye_runs() {
        return legbye_runs;
    }

    public String getNoball_runs() {
        return noball_runs;
    }

    public String getPenalty_runs() {
        return penalty_runs;
    }

    public String getBatsman_runs() {
        return batsman_runs;
    }

    public String getExtra_runs() {
        return extra_runs;
    }

    public String getTotal_runs() {
        return total_runs;
    }

    public String getPlayer_dismissed() {
        return player_dismissed;
    }

    public String getDismissal_kind() {
        return dismissal_kind;
    }


    @Override
    public String toString() {
        return "Deliveries{" +
                "match_id='" + match_id + '\'' +
                ", inning='" + inning + '\'' +
                ", batting_team='" + batting_team + '\'' +
                ", bowling_team='" + bowling_team + '\'' +
                ", over='" + over + '\'' +
                ", ball='" + ball + '\'' +
                ", batsman='" + batsman + '\'' +
                ", non_striker='" + non_striker + '\'' +
                ", bowler='" + bowler + '\'' +
                ", is_super_over='" + is_super_over + '\'' +
                ", wide_runs='" + wide_runs + '\'' +
                ", bye_runs='" + bye_runs + '\'' +
                ", legbye_runs='" + legbye_runs + '\'' +
                ", noball_runs='" + noball_runs + '\'' +
                ", penalty_runs='" + penalty_runs + '\'' +
                ", batsman_runs='" + batsman_runs + '\'' +
                ", extra_runs='" + extra_runs + '\'' +
                ", total_runs='" + total_runs + '\'' +
                ", player_dismissed='" + player_dismissed + '\'' +
                ", dismissal_kind='" + dismissal_kind + '\'' +
                '}';
    }
}
