package com.akashmjain.ipl;

import javax.xml.transform.Result;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.function.BiConsumer;

public class IPLTestDrive {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ipl_db";
    private static final String DB_USER_NAME = "postgres";
    private static final String DB_USER_PASSWORD = "password";

    private final static String matchFile    = "./data/matches.csv";
    private final static String deliveryFile = "./data/deliveries.csv";

    private static final int MATCH_ID = 1;
    private static final int MATCH_SEASON = 2;
    private static final int MATCH_CITY = 3;
    private static final int MATCH_DATE = 4;
    private static final int MATCH_TEAM1 = 5;
    private static final int MATCH_TEAM2 = 6;
    private static final int MATCH_TOSS_WINNER = 7;
    private static final int MATCH_TOSS_DECISION= 8;
    private static final int MATCH_RESULT = 9;
    private static final int MATCH_DL_APPLIED = 10;
    private static final int MATCH_WINNER = 11;
    private static final int MATCH_WIN_BY_RUNS = 12;
    private static final int MATCH_WIN_BY_WICKETS = 13;
    private static final int MATCH_PLAYER_OF_MATCH = 14;
    private static final int MATCH_VENUE = 15;
    private static final int MATCH_UMPIRE1 = 16;
    private static final int MATCH_UMPIRE2 = 17;
    private static final int MATCH_UMPIRE3 = 18;

    private static final int DELIVERY_MATCH_ID = 1;
    private static final int DELIVERY_INNING = 2;
    private static final int DELIVERY_BATTING_TEAM = 3;
    private static final int DELIVERY_BOWLING_TEAM = 4;
    private static final int DELIVERY_OVER = 5;
    private static final int DELIVERY_BALL = 6;
    private static final int DELIVERY_BATSMAN = 7;
    private static final int DELIVERY_NON_STRIKER = 8;
    private static final int DELIVERY_BOWLER = 9;
    private static final int DELIVERY_IS_SUPER_OVER = 10;
    private static final int DELIVERY_WIDE_RUNS = 11;
    private static final int DELIVERY_BYE_RUNS = 12;
    private static final int DELIVERY_LEGBYE_RUNS = 13;
    private static final int DELIVERY_NOBALL_RUNS = 14;
    private static final int DELIVERY_PENALTY_RUNS = 15;
    private static final int DELIVERY_BATSMAN_RUNS= 16;
    private static final int DELIVERY_EXTRA_RUNS = 17;
    private static final int DELIVERY_TOTAL_RUNS = 18;
    private static final int DELIVERY_PLAYER_DISMISSED = 19;
    private static final int DELIVERY_DISMISSAL_KIND = 20;
    private static final int DELIVERY_FIELDER = 21;

    public static void main(String[] args) {
        List<Match> matches = getMatchesData();
        List<Delivery> deliveries = getDeliveryData();
        findNumberOfMatchesWonPerTeamsOverAllYears(matches);
        findNumberOfMatchesPlayedPerYearForAllYears(matches);
        findYearWiseExtraRunConcededPerTeam(deliveries, matches, "2016");
        findYearWiseTopEconomicalBowlers(deliveries, matches,"2015", 5);
        findTopMostCatchesInHistoryPlayers(deliveries, 5);
    }

    private static void findNumberOfMatchesWonPerTeamsOverAllYears(List<Match> matches) {
        Map<String, Integer> noOfMatchesWonPerTeam = new HashMap<>();
        for(Match match : matches) {
            noOfMatchesWonPerTeam.putIfAbsent(match.getWinner(), 0);
            noOfMatchesWonPerTeam.put(match.getWinner(), noOfMatchesWonPerTeam.get(match.getWinner()) + 1);
        }
        noOfMatchesWonPerTeam.forEach((s, integer) -> System.out.println(s + " : " + integer));
    }

    private static void findNumberOfMatchesPlayedPerYearForAllYears(List<Match> matches) {
        Map<String, Integer> noOfMatchesPerYear = new HashMap<>();
        for(Match match : matches ) {
            noOfMatchesPerYear.putIfAbsent(match.getSeason(), 0);
            noOfMatchesPerYear.put(match.getSeason(), noOfMatchesPerYear.get(match.getSeason()) + 1);
        }
        noOfMatchesPerYear.forEach((s, integer) -> System.out.println(s + " : " + integer));
    }

    private static void findYearWiseExtraRunConcededPerTeam(List<Delivery> deliveries, List<Match> matches, String year) {
        Map<String, Integer> teamToRunHashMap = new HashMap<>();
        for(Match match : matches) {
            if(!match.getSeason().equals(year)) continue;
            for(Delivery delivery : deliveries) {
                if(!match.getId().equals(delivery.getMatchId())) continue;
                int run = Integer.parseInt(delivery.getExtraRuns());
                String key = delivery.getBattingTeam();
                Integer value = teamToRunHashMap.get(key) == null ? run : (teamToRunHashMap.get(key) + run);
                teamToRunHashMap.put(key, value);
            }
        }
        teamToRunHashMap.forEach((s, integer) -> System.out.println(s + " : " + integer));
    }

    private static void findYearWiseTopEconomicalBowlers(List<Delivery> deliveries, List<Match> matches, String year, int top) {
        Map<String, Bowler> bowlerHashMap = new HashMap<>();
        for(Match match : matches) {
            if(!match.getSeason().equals(year)) continue;
            for(Delivery delivery : deliveries) {
                if(!match.getId().equals(delivery.getMatchId())) continue;
                int run = Integer.parseInt(delivery.getTotalRuns());
                String key = delivery.getBowler();
                String lastOverID = delivery.getMatchId()+delivery.getOver();
                Bowler bowler = bowlerHashMap.get(key);

                if(bowler == null) {
                    String bowlerName = delivery.getBowler();
                    bowler = new Bowler();
                    bowler.setName(bowlerName);
                    bowler.setRun(run);
                    bowler.incrementOver();
                    bowler.setLastOver(lastOverID);
                }
                else {
                    if (!bowler.getLastOver().equals(lastOverID)) {
                        bowler.incrementOver();
                        bowler.setLastOver(lastOverID);
                    }
                    bowler.setRun(run + bowler.getRun());
                }
                bowler.incrementTotalBall();
                bowlerHashMap.put(key, bowler);
            }
        }
        Map<String, Float> playerEconomyMap = new HashMap<>();
        bowlerHashMap.forEach((playerName, bowler) -> {
            float economyRate = ((float) bowler.getRun()) / ((float) bowler.getOver());
            playerEconomyMap.put(playerName, economyRate);
        });
        List<Map.Entry<String, Float> > list = new LinkedList<>(playerEconomyMap.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<String, Float> sortedLinkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < top; i++) {
            Map.Entry<String, Float> aa = list.get(i);
            sortedLinkedHashMap.put(aa.getKey(), aa.getValue());
        }
        sortedLinkedHashMap.forEach((s, aFloat) -> System.out.println(s + " : " + aFloat));
    }

    private static void findTopMostCatchesInHistoryPlayers(List<Delivery> deliveries, int top){
        Map<String, Integer> hashMap = new HashMap<>();
        for(Delivery delivery : deliveries) {
            if (delivery.getDismissalKind().equals("caught")) {
                String fielder = delivery.getFielder().equals("") ? "UN-KNOWN" : delivery.getFielder();
                int noOfCatches = hashMap.get(fielder) == null ? 1 : hashMap.get(fielder) + 1;
                hashMap.put(fielder, noOfCatches);
            }
        }
        List<Map.Entry<String, Integer>> list = new LinkedList<>(hashMap.entrySet());
        list.sort((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));
        HashMap<String, Integer> topMost = new LinkedHashMap<>();
        for (int i = 0; i < top; i++) {
            Map.Entry<String, Integer> aa = list.get(i);
            topMost.put(aa.getKey(), aa.getValue());
        }
        topMost.forEach((s, integer) -> System.out.println(s + " : " + integer));
    }

    private static List<Delivery> getDeliveryData() {
        List<Delivery> deliveries = new ArrayList<>();
        String query = "SELECT * FROM deliveries;";
        try {
            Connection conn = null;
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet rows = statement.executeQuery(query);
            while(rows.next()) {
                Delivery delivery = new Delivery();
                delivery.setMatchId(rows.getString(DELIVERY_MATCH_ID));
                delivery.setBattingTeam(rows.getString(DELIVERY_BATTING_TEAM));
                delivery.setExtraRuns(rows.getString(DELIVERY_EXTRA_RUNS));
                delivery.setBowler(rows.getString(DELIVERY_BOWLER));
                delivery.setOver(rows.getString(DELIVERY_OVER));
                delivery.setTotalRuns(rows.getString(DELIVERY_TOTAL_RUNS));
                delivery.setIsSuperOver(rows.getString(DELIVERY_IS_SUPER_OVER));
                delivery.setDismissalKind(rows.getString(DELIVERY_DISMISSAL_KIND) == null ? "" : rows.getString(DELIVERY_DISMISSAL_KIND));
                delivery.setFielder(rows.getString(DELIVERY_FIELDER));
                deliveries.add(delivery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    private static List<Match> getMatchesData() {
        List<Match> matches = new ArrayList<>();
        String query = "SELECT * FROM matches;";
        try {
            Connection conn = null;
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet rows = statement.executeQuery(query);

            while(rows.next()) {
                Match match = new Match();
                match.setId(rows.getString(MATCH_ID));
                match.setWinner(rows.getString(MATCH_WINNER));
                match.setSeason(rows.getString(MATCH_SEASON));
                matches.add(match);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }
}
