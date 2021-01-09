package com.akashmjain.ipl;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BiConsumer;

public class IPLTestDrive {

    /* CSV Extraction */
    private final static String matchFile    = "./data/matches.csv";
    private final static String deliveryFile = "./data/deliveries.csv";

    /* Match TUPLES  make everything private*/
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

    /* Delivery tuples everything private */
    static final int DELIVERY_MATCH_ID = 0;
    static final int DELIVERY_INNING         = 1;
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
        List<Match> matches = getMatchesData(); // write parent here
        List<Delivery> deliveries = getDeliveriesData();

//        change all the methods name appropriately
        findNumberOfMatchesWonPerTeamsOverAllYears(matches);
        numberOfMatchesPlayedPerYearForAllYear(matches);
        yearWiseExtraRunConcededPerTeam(deliveries, matches, "2016");
        yearWiseTopEconomicalBowler(deliveries, matches,"2015", 5);
        topMostCatchesInHistoryPlayers(deliveries, 5);
    }

    private static void findNumberOfMatchesWonPerTeamsOverAllYears(List<Match> matches) {
        HashMap<String, LinkedList<Match>> matchesWonPerTeam = new HashMap<>();
        for(Match match : matches) {
            LinkedList<Match> linkedList = pushElementIntoLinkedList(match, match.getWinner(), matchesWonPerTeam);
            // @TODO: dont do un necessary stuff which is not a problem statement.
            String winner = match.getWinner().equals("") ? "NO WINNER" : match.getWinner();
            matchesWonPerTeam.put(winner, linkedList);
        }
        // @TODO: dont implement linked list do counter instead on each hit
        HashMap<String, Integer> noOfMatchesWonPerTeam = new HashMap<>();
        matchesWonPerTeam.forEach(new BiConsumer<String, LinkedList<Match>>() {
            @Override
            public void accept(String s, LinkedList<Match> matches) {
                noOfMatchesWonPerTeam.put(s, matches.size());
            }
        });

        utilityLog("number Of Matches Won of all teams over all year");
        utilityPrintResult(noOfMatchesWonPerTeam);
    }
    /**
     * 2. Number of matches won of all teams over all the years of IPL.
     * */
    // @TODO: Change
    private static void numberOfMatchesPlayedPerYearForAllYear(ArrayList<Match> matches) {
        utilityLog("number Of Matches played per year for all years");
        HashMap<String, LinkedList<Match>> matchesPerYear = new HashMap<>();
        for(Match match : matches ) {
            String key = match.getSeason();
            LinkedList<Match> linkedList = pushElementIntoLinkedList(match, key, matchesPerYear);
            matchesPerYear.put(key, linkedList);
        }
        HashMap<String, Integer> noOfMatchesPerYear = new HashMap<>();
        matchesPerYear.forEach(new BiConsumer<String, LinkedList<Match>>() {
            @Override
            public void accept(String s, LinkedList<Match> matches) {
                noOfMatchesPerYear.put(s, matches.size());
            }
        });
        utilityPrintResult(noOfMatchesPerYear);
    }


    /**
     * 3. For the year 2016 get the extra runs conceded per team.
     * */
    // @TODO: Merge all methods in one for each problems
    private static void yearWiseExtraRunConcededPerTeam(ArrayList<Delivery> deliveries, ArrayList<Match> matches,String year) {
//        utilityLog("year Wise Extra Run Conceded Per Team in year " + year);
        utilityPrintResult(deliveryWithMatchId(deliveries, matches,year));
    }
    private static HashMap<String, Integer> deliveryWithMatchId(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year) {
        ArrayList<Match> yearWiseMatches = filterByYear(matches, year);
        ArrayList<Delivery> yearWiseDelivery = filterDeliveryByMatchID(deliveries ,yearWiseMatches);
        return teamViseRuns(yearWiseDelivery);
    }
    private static HashMap<String, Integer> teamViseRuns(ArrayList<Delivery> deliveries) {
        HashMap<String, Integer> teamToRunHashMap = new HashMap<>();
        for(Delivery delivery : deliveries) {
            String key = delivery.getBattingTeam();
            int run = Integer.parseInt(delivery.getExtraRuns());
            Integer value = teamToRunHashMap.get(key) == null ? run : (teamToRunHashMap.get(key) + run);
            teamToRunHashMap.put(key, value);
        }
        return teamToRunHashMap;
    }


    /**
     * 4. For the year 2015 get the top economical bowlers.
     * */
    private static void yearWiseTopEconomicalBowler(ArrayList<Delivery> deliveries, ArrayList<Match> matches,String year, int top) {

        utilityLog("year Wise top economical Bowler in year " + year);
        ArrayList<Match> filteredByYear = filterByYear(matches, year); // get matches Played in year
        ArrayList<Delivery> filteredDelivery = filterDeliveryByMatchID(deliveries ,filteredByYear);
        HashMap<String, Float> hashMap = calculateEconomicRate(filteredDelivery);
        hashMap = getTopBestEconomicBowler(hashMap, top);
        utilityPrintResult(hashMap);
    }
    /* Get top economical bowlers based on there economy rate */
    private static HashMap<String, Float> getTopBestEconomicBowler(HashMap<String, Float> hm, int top) {
        List<Map.Entry<String, Float> > list = new LinkedList<Map.Entry<String, Float> >(hm.entrySet());

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
        return sortedHashMap;
    }
    /* Calculate economy rate for each bowler */
    private static HashMap<String, Float> calculateEconomicRate(ArrayList<Delivery> filteredDelivery) {
        HashMap<String, Bowler> bowlerHashMap = getBowlerMap(filteredDelivery);
        return getBowlerEconomyMap(bowlerHashMap);
    }
    /* Get Bowler name to Bowler Object HashMap for easy operation*/
    private static HashMap<String, Float> getBowlerEconomyMap(HashMap<String, Bowler> bowlerHashMap) {
        HashMap<String, Float> playerEconomyMap = new HashMap<>();
        bowlerHashMap.forEach(new BiConsumer<String, Bowler>() {
            @Override
            public void accept(String playerName, Bowler bowler) {
                float economyRate = ((float) bowler.getRun()) / ((float) bowler.getOver());
                playerEconomyMap.put(playerName, economyRate);
            }
        });
        return playerEconomyMap;
    }


    /* Returns a HashMap where key is a Bowler Name and value is a bowler object which contains all the data related to bowler.
     * See Bowler Class For more details
     */
    private static HashMap<String, Bowler> getBowlerMap(ArrayList<Delivery> deliveries) {
        HashMap<String, Bowler> bowlerHashMap = new HashMap<>();
        for(Delivery delivery : deliveries) {
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
        return bowlerHashMap;
    }

    /**
     * 5. get top players who caught maximum catches in history of IPL.
     * */
    private static void topMostCatchesInHistoryPlayers (ArrayList < Delivery > deliveries,int top){
        utilityLog("top player caught count");
        HashMap<String, Integer> hashMap = getCatchesByEachPlayer(deliveries);
        hashMap = getTopMostCatchesPlayers(hashMap, top);
        utilityPrintResult(hashMap);
    }
    // Sorting HashMap of type String -> Integer in descending order and returning top most results.
    private static HashMap<String, Integer> getTopMostCatchesPlayers(HashMap<String, Integer> hm, int top) {
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        HashMap<String, Integer> topMost = new LinkedHashMap<String, Integer>();

        for (int i = 0; i < top; i++) {
            Map.Entry<String, Integer> aa = list.get(i);
            topMost.put(aa.getKey(), aa.getValue());
        }
        return topMost;
    }
    // Returns the hashmap of player name as a key and number of catches he caught as a Integer.
    private static HashMap<String,Integer> getCatchesByEachPlayer(ArrayList<Delivery> list) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        Integer noOfCatches;
        String fielder;
        for(Delivery delivery : list) {
            if(!delivery.getDismissalKind().equals("caught")) {
                continue;
            }
            fielder     = delivery.getFielder().equals("") ? "UN-KNOWN" : delivery.getFielder();
            noOfCatches = hashMap.get(fielder) == null ? 1 : hashMap.get(fielder) + 1;
            hashMap.put(fielder, noOfCatches);
        }
        return hashMap;
    }

    /********************* UTILITY METHODS FOR PROBLEMS ***************************/
    private static LinkedList<Match> pushElementIntoLinkedList(Match match, String key,HashMap<String, LinkedList<Match>> hmap) {
        LinkedList<Match> linkedList = hmap.get(key) == null ? new LinkedList<>() : hmap.get(key);
        linkedList.add(match);
        return linkedList;
    }

    private static ArrayList<Match> filterByYear(ArrayList<Match> matches, String year) {
        ArrayList<Match> yearlySortedMatchArray = new ArrayList<>();
        for(Match match : matches) {
            if(match.getSeason().equals(year)) {
                yearlySortedMatchArray.add(match);
            }
        }
        return yearlySortedMatchArray;
    }

    private static ArrayList<Delivery> filterDeliveryByMatchID(ArrayList<Delivery> deliveries,ArrayList<Match> filteredMatches) {
        ArrayList<Delivery> sortedDeliveries = new ArrayList<>();
        for(Match match : filteredMatches) {
            for(Delivery delivery : deliveries) {
                if(match.getId().equals(delivery.getMatchId())) {
                    sortedDeliveries.add(delivery);
                }
            }
        }
        return sortedDeliveries;
    }


    /* other utility methods */
    // @TODO: dont create seperate utility for small things
    private static void utilityLog(String str) {
        System.out.println("==============================================================");
        System.out.println(str.toUpperCase());
        System.out.println("==============================================================");
    }

    private static void utilityPrintResult(HashMap<?, ?> list) {
        list.forEach(new BiConsumer<Object, Object>() {
            @Override
            public void accept(Object o1, Object o2) {
                System.out.println(o1 + " : " + o2);
            }
        });
    }


    /********************************* CSV FILES PARSING ****************************
     * This Section of program is to convert csv to native data set. so that it can be
     * easily manipulated for all the problems given.
    *********************************************************************************/
    /*
    * Match csv parsing starts here
    * */
    private static ArrayList<Match> getMatchesData() {
        ArrayList<Match> matches = null;
        try {
            File file = new File(matchFile);
            matches = formatDataForMatches(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }
    // @TODO: only 8 methods including main method.
    private static ArrayList<Match> formatDataForMatches(File file) {
        ArrayList<Match> matches = new ArrayList<>();
        ArrayList<String> lines =  separateLines(file);
        for(String line : lines) {
            matches.add(createMatchObject(line));
        }
        return matches;
    }
    private static Match createMatchObject(String matchTuple) {
        // @TODO: dont create String variables just push that in match object.
        ArrayList<String> list =  readTillCommaAndWhateverYouReadPlaceItIntoAStringArray(matchTuple);
        String id = list.get(MATCH_ID);
        String season = list.get(MATCH_SEASON);
        String city = list.get(MATCH_CITY);
        String date = list.get(MATCH_DATE);
        String team1         = list.get(MATCH_TEAM1);
        String team2         = list.get(MATCH_TEAM2);
        String tossWinner    = list.get(MATCH_TOSS_WINNER);
        String tossDecision  = list.get(MATCH_TOSS_DECISION);
        String result        = list.get(MATCH_RESULT);
        String dlApplied     = list.get(MATCH_DL_APPLIED);
        String winner        = list.get(MATCH_WINNER);
        String winByRuns     = list.get(MATCH_WIN_BY_RUNS);
        String winByWickets  = list.get(MATCH_WIN_BY_WICKETS);
        String playerOfMatch = list.get(MATCH_PLAYER_OF_MATCH);
        String venue         = list.get(MATCH_VENUE);
        String umpire1       = list.get(MATCH_UMPIRE1);
        String umpire2       = list.get(MATCH_UMPIRE2);
        String umpire3       = list.get(MATCH_UMPIRE3);

        Match match = new Match();
        match.setId(id);
        match.setSeason(season);
        match.setCity(city);
        match.setDate(date);
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setTossWinner(tossWinner);
        match.setTossDecision(tossDecision);
        match.setResult(result);
        match.setDlApplied(dlApplied);
        match.setWinner(winner);
        match.setWinByRuns(winByRuns);
        match.setWinByWickets(winByWickets);
        match.setPlayerOfMatch(playerOfMatch);
        match.setVenue(venue);
        match.setUmpire1(umpire1);
        match.setUmpire2(umpire2);
        match.setUmpire3(umpire3);

        return match;
    }

    /*
     * Delivery csv parsing starts from here
     */
    private static ArrayList<Delivery> getDeliveriesData() {
        ArrayList<Delivery> deliveries = null;
        try {
            File file = new File(deliveryFile);
            deliveries = formatDataForDelivery(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deliveries;
    }
    private static ArrayList<Delivery> formatDataForDelivery(File file) {
        ArrayList<Delivery> deliveries = new ArrayList<>();

        ArrayList<String> lines =  separateLines(file);
        for(String line : lines) {
            deliveries.add(createDeliveryObject(line));
        }
        return deliveries;
    }
    private static Delivery createDeliveryObject(String deliveryTuple) {
        ArrayList<String> list = readTillCommaAndWhateverYouReadPlaceItIntoAStringArray(deliveryTuple);

        String matchId         = list.get(DELIVERY_MATCH_ID);
        String inning          = list.get(DELIVERY_INNING);
        String battingTeam     = list.get(DELIVERY_BATTING_TEAM);
        String bowlingTeam     = list.get(DELIVERY_BOWLING_TEAM);
        String over            = list.get(DELIVERY_OVER);
        String ball            = list.get(DELIVERY_BALL);
        String batsman         = list.get(DELIVERY_BATSMAN);
        String nonStriker      = list.get(DELIVERY_NON_STRIKER);
        String bowler          = list.get(DELIVERY_BOWLER);
        String isSuperOver     = list.get(DELIVERY_IS_SUPER_OVER);
        String wideRuns        = list.get(DELIVERY_WIDE_RUNS);
        String byeRuns         = list.get(DELIVERY_BYE_RUNS);
        String legbyeRuns      = list.get(DELIVERY_LEGBYE_RUNS);
        String noballRuns      = list.get(DELIVERY_NOBALL_RUNS);
        String penaltyRuns     = list.get(DELIVERY_PENALTY_RUNS);
        String batsmanRuns     = list.get(DELIVERY_BATSMAN_RUNS);
        String extraRuns       = list.get(DELIVERY_EXTRA_RUNS);
        String totalRuns       = list.get(DELIVERY_TOTAL_RUNS);
        String playerDismissed = list.get(DELIVERY_PLAYER_DISMISSED);
        String dismissalKind   = list.get(DELIVERY_DISMISSAL_KIND);
        String fielder         = list.size() == 21 ? list.get(DELIVERY_FIELDER) : ""; // if last element exist assign its value

        Delivery delivery = new Delivery();
        delivery.setMatchId(matchId);
        delivery.setInning(inning);
        delivery.setBattingTeam(battingTeam);
        delivery.setBowlingTeam(bowlingTeam);
        delivery.setOver(over);
        delivery.setBall(ball);
        delivery.setBatsman(batsman);
        delivery.setNonStriker(nonStriker);
        delivery.setBowler(bowler);
        delivery.setIsSuperOver(isSuperOver);
        delivery.setWideRuns(wideRuns);
        delivery.setByeRuns(byeRuns);
        delivery.setLegbyeRuns(legbyeRuns);
        delivery.setNoballRuns(noballRuns);
        delivery.setPenaltyRuns(penaltyRuns);
        delivery.setBatsmanRuns(batsmanRuns);
        delivery.setExtraRuns(extraRuns);
        delivery.setTotalRuns(totalRuns);
        delivery.setPlayerDismissed(playerDismissed);
        delivery.setDismissalKind(dismissalKind);
        delivery.setFielder(fielder);

        return delivery;
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
        // if last is comma and is empty, add extra empty string to list

        if(s.length() > 0) {
            list.add(s);
        }
        if(line.charAt(line.length() - 1) == ',') {
            list.add("");
        }

        return list;
    }

}
 