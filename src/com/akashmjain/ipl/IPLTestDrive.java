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
        ArrayList<Match>    matches    = parseMatchCSV();
        ArrayList<Delivery> deliveries = parseDeliveryCSV();
        findNumberOfMatchesWonPerTeamsOverAllYears(matches);
        findNumberOfMatchesPlayedPerYearForAllYears(matches);
        findYearWiseExtraRunConcededPerTeam(deliveries, matches, "2016");
        findYearWiseTopEconomicalBowlers(deliveries, matches,"2015", 5);
        findTopMostCatchesInHistoryPlayers(deliveries, 5);
    }

    private static void findNumberOfMatchesWonPerTeamsOverAllYears(ArrayList<Match> matches) {

        HashMap<String, LinkedList<Match>> matchesWonPerTeam = new HashMap<>();
        HashMap<String, Integer> noOfMatchesWonPerTeam = new HashMap<>();
        for(Match match : matches) {
            noOfMatchesWonPerTeam.putIfAbsent(match.getWinner(), 0);
            noOfMatchesWonPerTeam.put(match.getWinner(), noOfMatchesWonPerTeam.get(match.getWinner()) + 1);
        }
        noOfMatchesWonPerTeam.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s + " : " + integer);
            }
        });
    }

    private static void findNumberOfMatchesPlayedPerYearForAllYears(ArrayList<Match> matches) {
        HashMap<String, LinkedList<Match>> matchesPerYear = new HashMap<>();
        HashMap<String, Integer> noOfMatchesPerYear = new HashMap<>();
        for(Match match : matches ) {
            noOfMatchesPerYear.putIfAbsent(match.getSeason(), 0);
            noOfMatchesPerYear.put(match.getSeason(), noOfMatchesPerYear.get(match.getSeason()) + 1);
        }
        noOfMatchesPerYear.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s + " : " + integer);
            }
        });
    }

    private static void findYearWiseExtraRunConcededPerTeam(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year) {
        ArrayList<Match> yearWiseMatches = new ArrayList<>();
        for(Match match : matches) {
            if(match.getSeason().equals(year)) {
                yearWiseMatches.add(match);
            }
        }
        ArrayList<Delivery> yearWiseDelivery = new ArrayList<>();
        for(Match match : yearWiseMatches) {
            for(Delivery delivery : deliveries) {
                if(match.getId().equals(delivery.getMatchId())) {
                    yearWiseDelivery.add(delivery);
                }
            }
        }
        HashMap<String, Integer> teamToRunHashMap = new HashMap<>();
        for(Delivery delivery : yearWiseDelivery) {
            String key = delivery.getBattingTeam();
            int run = Integer.parseInt(delivery.getExtraRuns());
            Integer value = teamToRunHashMap.get(key) == null ? run : (teamToRunHashMap.get(key) + run);
            teamToRunHashMap.put(key, value);
        }
        teamToRunHashMap.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s + " : " + integer);
            }
        });
    }

    private static void findYearWiseTopEconomicalBowlers(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year, int top) {
        ArrayList<Match> matchesFilteredByYear = new ArrayList<>();
        for(Match match : matches) {
            if(match.getSeason().equals(year)) {
                matchesFilteredByYear.add(match);
            }
        }
        ArrayList<Delivery> yearWiseDeliveries = new ArrayList<>();
        for(Match match : matchesFilteredByYear) {
            for(Delivery delivery : deliveries) {
                if(match.getId().equals(delivery.getMatchId())) {
                    yearWiseDeliveries.add(delivery);
                }
            }
        }
        HashMap<String, Bowler> bowlerHashMap = new HashMap<>();
        for(Delivery delivery : yearWiseDeliveries) {
            int run           = Integer.parseInt(delivery.getTotalRuns());
            String key        = delivery.getBowler();
            String lastOverID = delivery.getMatchId()+delivery.getOver();
            Bowler bowler     = bowlerHashMap.get(key);
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
        HashMap<String, Float> playerEconomyMap = new HashMap<>();
        bowlerHashMap.forEach(new BiConsumer<String, Bowler>() {
            @Override
            public void accept(String playerName, Bowler bowler) {
                float economyRate = ((float) bowler.getRun()) / ((float) bowler.getOver());
                playerEconomyMap.put(playerName, economyRate);
            }
        });
        List<Map.Entry<String, Float> > list = new LinkedList<Map.Entry<String, Float> >(playerEconomyMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Float> >() {
            public int compare(Map.Entry<String, Float> o1,
                               Map.Entry<String, Float> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<String, Float> sortedHashMap = new LinkedHashMap<String, Float>();
        for (int i = 0; i < top; i++) {
            Map.Entry<String, Float> aa = list.get(i);
            sortedHashMap.put(aa.getKey(), aa.getValue());
        }
        sortedHashMap.forEach(new BiConsumer<String, Float>() {
            @Override
            public void accept(String s, Float aFloat) {
                System.out.println(s + " : " + aFloat);
            }
        });
    }

    private static void findTopMostCatchesInHistoryPlayers(ArrayList<Delivery> deliveries, int top){
        HashMap<String, Integer> hashMap = new HashMap<>();
        Integer noOfCatches;
        String fielder;
        for(Delivery delivery : deliveries) {
            if(!delivery.getDismissalKind().equals("caught")) {
                continue;
            }
            fielder = delivery.getFielder().equals("") ? "UN-KNOWN" : delivery.getFielder();
            noOfCatches = hashMap.get(fielder) == null ? 1 : hashMap.get(fielder) + 1;
            hashMap.put(fielder, noOfCatches);
        }

    }

    private static ArrayList<Delivery> parseDeliveryCSV() {
        ArrayList<Delivery> deliveries = new ArrayList<>();
        try {
            File file = new File(deliveryFile);
            ArrayList<String> lines = new ArrayList<>();
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
                delivery.setDismissalKind(list.size() == DELIVERY_DISMISSAL_KIND + 1 ? list.get(DELIVERY_DISMISSAL_KIND) : "");
                delivery.setFielder(list.size() == DELIVERY_FIELDER + 1 ?list.get(DELIVERY_FIELDER) : "");
                deliveries.add(delivery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deliveries;
    }
    private static ArrayList<Match> parseMatchCSV() {
        ArrayList<Match> matches = new ArrayList<>();
        try {
            File file = new File(matchFile);
            ArrayList<String> lines = new ArrayList<>();
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
