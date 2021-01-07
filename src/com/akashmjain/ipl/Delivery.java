package com.akashmjain.ipl;

public class Delivery {
    private final String matchId;
    private final String inning;
    private final String battingTeam;
    private final String bowlingTeam;
    private final String over;
    private final String ball;
    private final String batsman;
    private final String nonStriker;
    private final String bowler;
    private final String isSuperOver;
    private final String wideRuns;
    private final String byeRuns;
    private final String legbyeRuns;
    private final String noballRuns;
    private final String penaltyRuns;
    private final String batsmanRuns;
    private final String extraRuns;
    private final String totalRuns;
    private final String playerDismissed;
    private final String dismissalKind;
    private final String fielder;

    public Delivery(
        String matchId,
        String inning,
        String battingTeam,
        String bowlingTeam,
        String over,
        String ball,
        String batsman,
        String nonStriker,
        String bowler,
        String isSuperOver,
        String wideRuns,
        String byeRuns,
        String legbyeRuns,
        String noballRuns,
        String penaltyRuns,
        String batsmanRuns,
        String extraRuns,
        String totalRuns,
        String playerDismissed,
        String dismissalKind,
        String fielder
        ) {
        this.matchId         = matchId;
        this.inning          = inning;
        this.battingTeam     = battingTeam;
        this.bowlingTeam     = bowlingTeam;
        this.over            = over;
        this.ball            = ball;
        this.batsman         = batsman;
        this.nonStriker      = nonStriker;
        this.bowler          = bowler;
        this.isSuperOver     = isSuperOver;
        this.wideRuns        = wideRuns;
        this.byeRuns         = byeRuns;
        this.legbyeRuns      = legbyeRuns;
        this.noballRuns      = noballRuns;
        this.penaltyRuns     = penaltyRuns;
        this.batsmanRuns     = batsmanRuns;
        this.extraRuns       = extraRuns;
        this.totalRuns       = totalRuns;
        this.playerDismissed = playerDismissed;
        this.dismissalKind   = dismissalKind;
        this.fielder         = fielder;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getInning() {
        return inning;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
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

    public String getNonStriker() {
        return nonStriker;
    }

    public String getBowler() {
        return bowler;
    }

    public String getIsSuperOver() {
        return isSuperOver;
    }

    public String getWideRuns() {
        return wideRuns;
    }

    public String getByeRuns() {
        return byeRuns;
    }

    public String getLegbyeRuns() {
        return legbyeRuns;
    }

    public String getNoballRuns() {
        return noballRuns;
    }

    public String getPenaltyRuns() {
        return penaltyRuns;
    }

    public String getBatsmanRuns() {
        return batsmanRuns;
    }

    public String getExtraRuns() {
        return extraRuns;
    }

    public String getTotalRuns() {
        return totalRuns;
    }

    public String getPlayerDismissed() {
        return playerDismissed;
    }

    public String getDismissalKind() {
        return dismissalKind;
    }

    public String getFielder() {
        return fielder;
    }

    @Override
    public String toString() {
        return "Deliveries{" +
                "matchId='" + matchId + '\'' +
                ", inning='" + inning + '\'' +
                ", battingTeam='" + battingTeam + '\'' +
                ", bowlingTeam='" + bowlingTeam + '\'' +
                ", over='" + over + '\'' +
                ", ball='" + ball + '\'' +
                ", batsman='" + batsman + '\'' +
                ", nonStriker='" + nonStriker + '\'' +
                ", bowler='" + bowler + '\'' +
                ", isSuperOver='" + isSuperOver + '\'' +
                ", wideRuns='" + wideRuns + '\'' +
                ", byeRuns='" + byeRuns + '\'' +
                ", legbyeRuns='" + legbyeRuns + '\'' +
                ", noballRuns='" + noballRuns + '\'' +
                ", penaltyRuns='" + penaltyRuns + '\'' +
                ", batsmanRuns='" + batsmanRuns + '\'' +
                ", extraRuns='" + extraRuns + '\'' +
                ", totalRuns='" + totalRuns + '\'' +
                ", playerDismissed='" + playerDismissed + '\'' +
                ", dismissalKind='" + dismissalKind + '\'' +
                ", fielder='" + fielder + '\'' +
                '}';
    }
}
