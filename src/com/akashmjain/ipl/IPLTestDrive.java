package com.akashmjain.ipl;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BiConsumer;

public class IPLTestDrive {
    private final static String matchFile    = "./data/matches.csv";
    private final static String deliveryFile = "./data/deliveries.csv";

    static final int MATCH_ID                   = 0;
    static final int MATCH_SEASON               = 1;
    static final int MATCH_CITY                 = 2;
    static final int MATCH_DATE                 = 3;
    static final int MATCH_TEAM1                = 4;
    static final int MATCH_TEAM2                = 5;
    static final int MATCH_TOSS_WINNER          = 6;
    static final int MATCH_TOSS_DECISION        = 7;
    static final int MATCH_RESULT               = 8;
    static final int MATCH_DL_APPLIED           = 9;
    static final int MATCH_WINNER               = 10;
    static final int MATCH_WIN_BY_RUNS          = 11;
    static final int MATCH_WIN_BY_WICKETS       = 12;
    static final int MATCH_PLAYER_OF_MATCH      = 13;
    static final int MATCH_VENUE                = 14;
    static final int MATCH_UMPIRE1              = 15;
    static final int MATCH_UMPIRE2              = 16;
    static final int MATCH_UMPIRE3              = 17;

    static final int DELIVERY_MATCH_ID          = 0;
    static final int DELIVERY_INNING            = 1;
    static final int DELIVERY_BATTING_TEAM      = 2;
    static final int DELIVERY_BOWLING_TEAM      = 3;
    static final int DELIVERY_OVER              = 4;
    static final int DELIVERY_BALL              = 5;
    static final int DELIVERY_BATSMAN           = 6;
    static final int DELIVERY_NON_STRIKER       = 7;
    static final int DELIVERY_BOWLER            = 8;
    static final int DELIVERY_IS_SUPER_OVER     = 9;
    static final int DELIVERY_WIDE_RUNS         = 10;
    static final int DELIVERY_BYE_RUNS          = 11;
    static final int DELIVERY_LEGBYE_RUNS       = 12;
    static final int DELIVERY_NOBALL_RUNS       = 13;
    static final int DELIVERY_PENALTY_RUNS      = 14;
    static final int DELIVERY_BATSMAN_RUNS      = 15;
    static final int DELIVERY_EXTRA_RUNS        = 16;
    static final int DELIVERY_TOTAL_RUNS        = 17;
    static final int DELIVERY_PLAYER_DISMISSED  = 18;
    static final int DELIVERY_DISMISSAL_KIND    = 19;
    static final int DELIVERY_FIELDER           = 20;

    public static void main(String[] args) {
        ArrayList<Match>    matches    = parseMatchCSV();
        ArrayList<Delivery> deliveries = parseDeliveryCSV();

//        findNumberOfMatchesWonPerTeamsOverAllYears(matches);
//        findNumberOfMatchesPlayedPerYearForAllYears(matches);
//        findYearWiseExtraRunConcededPerTeam(deliveries, matches, "2016");
//        findYearWiseTopEconomicalBowlers(deliveries, matches,"2015", 5);
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
        // Boweler Map
        HashMap<String, Bowler> bowlerHashMap = new HashMap<>();
        for(Delivery delivery : yearWiseDeliveries) {
            int run           = Integer.parseInt(delivery.getTotalRuns());
            String key        = delivery.getBowler();
            String lastOverID = delivery.getMatchId()+delivery.getOver();
            Bowler bowler     = bowlerHashMap.get(key);
            // Create new bowler object if there is no entry for Bowler name
            if(bowler == null) {
                String bowlerName = delivery.getBowler();
                bowler = new Bowler();
                bowler.setName(bowlerName);
                bowler.setRun(run);
                bowler.incrementOver();
                bowler.setLastOver(lastOverID);
            }
            // if bowler already exist in HashMap.
            else {
                // If over change increment over value.
                if (!bowler.getLastOver().equals(lastOverID)) {
                    bowler.incrementOver();
                    bowler.setLastOver(lastOverID);
                }
                // add run to existing run of bowler
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
            fielder     = delivery.getFielder().equals("") ? "UN-KNOWN" : delivery.getFielder();
            noOfCatches = hashMap.get(fielder) == null ? 1 : hashMap.get(fielder) + 1;
            hashMap.put(fielder, noOfCatches);
        }
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer> >(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        HashMap<String, Integer> topMost = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < top; i++) {
            Map.Entry<String, Integer> aa = list.get(i);
            topMost.put(aa.getKey(), aa.getValue());
        }
        topMost.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s + " : " + integer);
            }
        });
    }
    /********************************* CSV FILES PARSING ****************************/
    private static ArrayList<Match> parseMatchCSV() {
        ArrayList<Match> matches = new ArrayList<>();
        try {
            File file = new File(matchFile);
            ArrayList<String> lines =  separateLines(file);
            for(String line : lines) {
                matches.add(createMatchObject(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

    private static Match createMatchObject(String matchTuple) {
        ArrayList<String> list =  readTillCommaAndWhateverYouReadPlaceItIntoAStringArray(matchTuple);
        Match match = new Match();
        match.setId(list.get(MATCH_ID));
        match.setSeason(list.get(MATCH_SEASON));
        match.setCity(list.get(MATCH_CITY));
        match.setDate(list.get(MATCH_DATE));
        match.setTeam1(list.get(MATCH_TEAM1));
        match.setTeam2(list.get(MATCH_TEAM2));
        match.setTossWinner(list.get(MATCH_TOSS_WINNER));
        match.setTossDecision(list.get(MATCH_TOSS_DECISION));
        match.setResult(list.get(MATCH_RESULT));
        match.setDlApplied(list.get(MATCH_DL_APPLIED));
        match.setWinner(list.get(MATCH_WINNER));
        match.setWinByRuns(list.get(MATCH_WIN_BY_RUNS));
        match.setWinByWickets(list.get(MATCH_WIN_BY_WICKETS));
        match.setPlayerOfMatch(list.get(MATCH_PLAYER_OF_MATCH));
        match.setVenue(list.get(MATCH_VENUE));
        match.setUmpire1(list.get(MATCH_UMPIRE1));
        match.setUmpire2(list.get(MATCH_UMPIRE2));
        match.setUmpire3(list.get(MATCH_UMPIRE3));
        return match;
    }
    /*
     * Delivery csv parsing starts from here
     */
    private static Delivery createDeliveryObject(String deliveryTuple) {
//        ArrayList<String> list = readTillCommaAndWhateverYouReadPlaceItIntoAStringArray(deliveryTuple);
        ArrayList<String>
        Delivery delivery = new Delivery();
        delivery.setMatchId(list.get(DELIVERY_MATCH_ID));
        delivery.setInning(list.get(DELIVERY_INNING));
        delivery.setBattingTeam(list.get(DELIVERY_BATTING_TEAM));
        delivery.setBowlingTeam(list.get(DELIVERY_BOWLING_TEAM));
        delivery.setOver(list.get(DELIVERY_OVER));
        delivery.setBall(list.get(DELIVERY_BALL));                                                                                                                                                                                                                                                                                                                                      
        delivery.setBatsman(list.get(DELIVERY_BATSMAN));
        delivery.setNonStriker(list.get(DELIVERY_NON_STRIKER));
        delivery.setBowler(list.get(DELIVERY_BOWLER));
        delivery.setIsSuperOver(list.get(DELIVERY_IS_SUPER_OVER));
        delivery.setWideRuns(list.get(DELIVERY_WIDE_RUNS));
        delivery.setByeRuns(list.get(DELIVERY_BYE_RUNS));
        delivery.setLegbyeRuns(list.get(DELIVERY_LEGBYE_RUNS));
        delivery.setNoballRuns(list.get(DELIVERY_NOBALL_RUNS));
        delivery.setPenaltyRuns(list.get(DELIVERY_PENALTY_RUNS));
        delivery.setBatsmanRuns(list.get(DELIVERY_BATSMAN_RUNS));
        delivery.setExtraRuns(list.get(DELIVERY_EXTRA_RUNS));
        delivery.setTotalRuns(list.get(DELIVERY_TOTAL_RUNS));
        delivery.setPlayerDismissed(list.get(DELIVERY_PLAYER_DISMISSED));
        delivery.setDismissalKind(list.get(DELIVERY_DISMISSAL_KIND));
        delivery.setFielder(list.size() == 21 ?list.get(DELIVERY_FIELDER) : "");
        return delivery;
    }
    private static ArrayList<Delivery> parseDeliveryCSV() {
        ArrayList<Delivery> deliveries = new ArrayList<>();
        try {
            File file = new File(deliveryFile);
            ArrayList<String> lines =  separateLines(file);
            for(String line : lines) {
                deliveries.add(createDeliveryObject(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    /* Utility Methods for csv data  parsing */
    private static ArrayList<String> separateLines(File file) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                lines.add(sc.nextLine());
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static ArrayList<String> readTillCommaAndWhateverYouReadPlaceItIntoAStringArray(String line) {
        ArrayList<String> list = new ArrayList<>();
        String s = "";
        for(int i = 0; i < line.length(); i++) {
            // @TODO seperate this if and else logic to different method.
            if(line.charAt(i) == ',') {
                list.add(s);
                s = "";
            } else {
                s += line.charAt(i);
            }
        }
        if(s.length() > 0) {
            list.add(s);
        }
        if(line.charAt(line.length() - 1) == ',') {
            list.add("");
        }
        return list;
    }
}
