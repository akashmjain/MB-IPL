package com.akashmjain.ipl;

import java.io.File;
import java.util.*;
import java.util.function.BiConsumer;

public class IPLTestDrive {
    private final static String matchFile    = "./data/matches.csv";
    private final static String deliveryFile = "./data/deliveries.csv";

    private static final int MATCH_ID = 0;
    private static final int MATCH_SEASON = 1;
    private static final int MATCH_CITY = 2;
    private static final int MATCH_DATE = 3;
    private static final int MATCH_TEAM1 = 4;
    private static final int MATCH_TEAM2 = 5;
    private static final int MATCH_TOSS_WINNER = 6;
    private static final int MATCH_TOSS_DECISION= 7;
    private static final int MATCH_RESULT = 8;
    private static final int MATCH_DL_APPLIED = 9;
    private static final int MATCH_WINNER = 10;
    private static final int MATCH_WIN_BY_RUNS = 11;
    private static final int MATCH_WIN_BY_WICKETS = 12;
    private static final int MATCH_PLAYER_OF_MATCH = 13;
    private static final int MATCH_VENUE = 14;
    private static final int MATCH_UMPIRE1 = 15;
    private static final int MATCH_UMPIRE2 = 16;
    private static final int MATCH_UMPIRE3 = 17;

    private static final int DELIVERY_MATCH_ID = 0;
    private static final int DELIVERY_INNING = 1;
    private static final int DELIVERY_BATTING_TEAM = 2;
    private static final int DELIVERY_BOWLING_TEAM = 3;
    private static final int DELIVERY_OVER = 4;
    private static final int DELIVERY_BALL = 5;
    private static final int DELIVERY_BATSMAN = 6;
    private static final int DELIVERY_NON_STRIKER = 7;
    private static final int DELIVERY_BOWLER = 8;
    private static final int DELIVERY_IS_SUPER_OVER = 9;
    private static final int DELIVERY_WIDE_RUNS = 10;
    private static final int DELIVERY_BYE_RUNS = 11;
    private static final int DELIVERY_LEGBYE_RUNS = 12;
    private static final int DELIVERY_NOBALL_RUNS = 13;
    private static final int DELIVERY_PENALTY_RUNS = 14;
    private static final int DELIVERY_BATSMAN_RUNS= 15;
    private static final int DELIVERY_EXTRA_RUNS = 16;
    private static final int DELIVERY_TOTAL_RUNS = 17;
    private static final int DELIVERY_PLAYER_DISMISSED = 18;
    private static final int DELIVERY_DISMISSAL_KIND = 19;
    private static final int DELIVERY_FIELDER = 20;

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
        try {
            File file = new File(deliveryFile);
            List<String> lines = new ArrayList<>();
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                lines.add(sc.nextLine());
            }
            for(String line : lines) {
                List<String> list = Arrays.asList(line.split(","));
                Delivery delivery = new Delivery();
                delivery.setMatchId(list.get(DELIVERY_MATCH_ID));
                delivery.setBattingTeam(list.get(DELIVERY_BATTING_TEAM));
                delivery.setExtraRuns(list.get(DELIVERY_EXTRA_RUNS));
                delivery.setBowler(list.get(DELIVERY_BOWLER));
                delivery.setOver(list.get(DELIVERY_OVER));
                delivery.setTotalRuns(list.get(DELIVERY_TOTAL_RUNS));
                delivery.setIsSuperOver(list.get(DELIVERY_IS_SUPER_OVER));
                delivery.setDismissalKind(list.size() >= DELIVERY_DISMISSAL_KIND ? list.get(DELIVERY_DISMISSAL_KIND) : "");
                delivery.setFielder(list.size() >= DELIVERY_FIELDER + 1 ?list.get(DELIVERY_FIELDER) : "");
                deliveries.add(delivery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    private static List<Match> getMatchesData() {
        List<Match> matches = new ArrayList<>();
        try {
            File file = new File(matchFile);
            List<String> lines = new ArrayList<>();
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                lines.add(sc.nextLine());
            }
            for(String line : lines) {
                List<String> list = Arrays.asList(line.split(","));
                Match match = new Match();
                match.setId(list.get(MATCH_ID));
                match.setWinner(list.get(MATCH_WINNER));
                match.setSeason(list.get(MATCH_SEASON));
                matches.add(match);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }
}
