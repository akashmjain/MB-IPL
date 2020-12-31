package com.akashmjain.ipl;

import com.akashmjain.ipl.filter.FilterInterface;
import com.akashmjain.ipl.filter.deliveriesFilter.EconomicalBowlers;
import com.akashmjain.ipl.filter.deliveriesFilter.ExtraConcededRunFilter;
import com.akashmjain.ipl.filter.matchFilter.MatchFilter;
import com.akashmjain.ipl.filter.matchFilter.WonMatchesPerTeamFilter;
import com.akashmjain.ipl.filter.matchFilter.YearMatchFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.function.BiConsumer;

public class FilterFacade implements FilterInterface {
    private final String matchFile = "./data/matches.csv";
    private final String deliveryFile = "./data/deliveries.csv";
    private final ArrayList<Delivery> deliveries;
    private final ArrayList<Match> matches;

    public FilterFacade() {
        matches = csvMatchTest();
        deliveries = csvDeliveryTest();
    }
    @Override
    public void numberOfMatchesPlayedPerYearForAllYear() {
        System.out.println("==============================================================");
        System.out.println("number Of Matches Played Per Year For All Year".toUpperCase());
        System.out.println("==============================================================");
        MatchFilter matchFilter = new YearMatchFilter();
        HashMap<String, LinkedList<Match>> hashMap = matchFilter.filter(matches);
        hashMap.forEach(new BiConsumer<String, LinkedList<Match>>() {
            @Override
            public void accept(String s, LinkedList<Match> matches) {
                System.out.println(s + " : " + matches);
            }
        });
    }
    @Override
    public void numberOfMatchesWonOfAllTeamsOverAllYear() {
        System.out.println("==============================================================");
        System.out.println("number Of Matches Won of all teams over all year".toUpperCase());
        System.out.println("==============================================================");

        MatchFilter matchFilter = new WonMatchesPerTeamFilter();
        HashMap<String, LinkedList<Match>> hashMap = matchFilter.filter(matches);
        hashMap.forEach(new BiConsumer<String, LinkedList<Match>>() {
            @Override
            public void accept(String s, LinkedList<Match> matches) {
                System.out.println(s + " : " + matches);
            }
        });
    }
    @Override
    public void yearWiseExtraRunConcededPerTeam(String year) {

        System.out.println("==============================================================");
        System.out.println("year Wise Extra Run Conceded Per Team".toUpperCase() + " IN YEAR " + year);
        System.out.println("==============================================================");


        ExtraConcededRunFilter deliveryFilter = new ExtraConcededRunFilter();
        HashMap<String, Integer> hashMap = deliveryFilter.filter(deliveries, matches,year);
        hashMap.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s + " : " + integer);
            }
        });
    }
    @Override
    public void yearWiseTopEconomicalBoweler(String year, int top) {

        System.out.println("==============================================================");
        System.out.println("year Wise top economical Boweler".toUpperCase() + " IN YEAR " + year);
        System.out.println("==============================================================");

        EconomicalBowlers deliveryFilter = new EconomicalBowlers();
        HashMap<String, Float> hashMap = deliveryFilter.filter(deliveries, matches, year, top);
        hashMap.forEach(new BiConsumer<String, Float>() {
            @Override
            public void accept(String s, Float aFloat) {
                System.out.println(s + " : " + aFloat);
            }
        });
    }





    private ArrayList<Match> csvMatchTest() {
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

    private ArrayList<Delivery> csvDeliveryTest() {
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
