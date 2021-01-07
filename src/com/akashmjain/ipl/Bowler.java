package com.akashmjain.ipl;

public class Bowler {
    private int run = 0;
    private int over = 0;
    private int totalBall = 0;
    private String name;
    private String lastOver;


    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getOver() {
        return over;
    }

    public void incrementOver() {
        this.over++;
    }

    public int getTotalBall() {
        return totalBall;
    }

    public void incrementTotalBall() {
        totalBall++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastOver() {
        return lastOver;
    }

    public void setLastOver(String lastOver) {
        this.lastOver = lastOver;
    }

    @Override
    public String toString() {
        return "Bowler{" +
                "run=" + run +
                ", over=" + over +
                ", totalBall=" + totalBall +
                ", name='" + name + '\'' +
                ", lastOver='" + lastOver + '\'' +
                '}';
    }
}
