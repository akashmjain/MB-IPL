package com.akashmjain.ipl;

import com.akashmjain.ipl.filter.deliveriesFilter.CatchFilter;
import com.akashmjain.ipl.filter.deliveriesFilter.EconomicalBowlers;
import com.akashmjain.ipl.filter.deliveriesFilter.ExtraConcededRunFilter;
import com.akashmjain.ipl.filter.matchFilter.MatchFilter;
import com.akashmjain.ipl.filter.matchFilter.WonMatchesPerTeamFilter;
import com.akashmjain.ipl.filter.matchFilter.YearMatchFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.BiConsumer;

public class FilterFacade implements FilterInterface {
    private final static String matchFile = "./data/matches.csv";
    private final static String deliveryFile = "./data/deliveries.csv";
    private final static ArrayList<Match> matches = csvMatchTest();
    private final static ArrayList<Delivery> deliveries = csvDeliveryTest();

    @Override
    public void numberOfMatchesPlayedPerYearForAllYear() {
        utilityLog("number Of Matches Won of all teams over all year");

        MatchFilter matchFilter = new YearMatchFilter();
        HashMap<String, LinkedList<Match>> hashMap = matchFilter.filter(matches);

        utilityPrintResult(hashMap);
    }
    @Override
    public void numberOfMatchesWonOfAllTeamsOverAllYear() {
        utilityLog("number Of Matches Won of all teams over all year");


        MatchFilter matchFilter = new WonMatchesPerTeamFilter();
        HashMap<String, LinkedList<Match>> hashMap = matchFilter.filter(matches);
        utilityPrintResult(hashMap);
    }
    @Override
    public void yearWiseExtraRunConcededPerTeam(String year) {

        utilityLog("year Wise Extra Run Conceded Per Team in year " + year);

        ExtraConcededRunFilter deliveryFilter = new ExtraConcededRunFilter();
        HashMap<String, Integer> hashMap = deliveryFilter.filter(deliveries, matches,year);

        utilityPrintResult(hashMap);
    }
    @Override
    public void yearWiseTopEconomicalBowler(String year, int top) {

        utilityLog("year Wise top economical Bowler in year" + year);
        EconomicalBowlers deliveryFilter = new EconomicalBowlers();
        HashMap<String, Float> hashMap = deliveryFilter.filter(deliveries, matches, year);
        hashMap = deliveryFilter.getTop(hashMap, top);
        utilityPrintResult(hashMap);
    }

    @Override
    public void topMostCatchesInHistoryPlayers(int top) {
        utilityLog("top player caught count");
        CatchFilter catchFilter = new CatchFilter();
        HashMap<String, Integer> hashMap = catchFilter.filter(deliveries);
        hashMap = catchFilter.getTop(hashMap, top);
        utilityPrintResult(hashMap);
    }

    // utility functions
    private void utilityPrintResult(HashMap<?, ?> list) {
        list.forEach(new BiConsumer<Object, Object>() {
            @Override
            public void accept(Object o1, Object o2) {
                System.out.println(o1 + " : " + o2);
            }
        });
    }
    private void utilityLog(String str) {
        System.out.println("==============================================================");
        System.out.println(str.toUpperCase());
        System.out.println("==============================================================");
    }


    private static ArrayList<Match> csvMatchTest() {
        ArrayList<Match> matches = null;
        try {
            File matchData = new File(matchFile);
            CSVHelper csvHelper = new CSVHelper(matchData);
            matches = csvHelper.formatDataForMatches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

    private static ArrayList<Delivery> csvDeliveryTest() {
        ArrayList<Delivery> deliveries = null;
        try {
            File deliveryData = new File(deliveryFile);
            CSVHelper csvHelper = new CSVHelper(deliveryData);
            deliveries = csvHelper.formatDataForDelivery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deliveries;
    }
}
