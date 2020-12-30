package com.akashmjain.ipl;

import com.akashmjain.ipl.filter.deliveriesFilter.DeliveryFilter;
import com.akashmjain.ipl.filter.deliveriesFilter.EconomicalBowlers;
import com.akashmjain.ipl.filter.deliveriesFilter.ExtraConcededRunFilter;
import com.akashmjain.ipl.filter.matchFilter.MatchFilter;
import com.akashmjain.ipl.filter.matchFilter.WonMatchesPerTeamFilter;
import com.akashmjain.ipl.filter.matchFilter.YearMatchFilter;

import java.io.File;
import java.util.*;
import java.util.function.BiConsumer;


public class IPLTestDrive {

    public static void main(String[] args) throws Exception {
        IPLTestDrive        iplTestDrive   = new IPLTestDrive();
        ArrayList<Match>    matches        = iplTestDrive.csvMatchTest();
        ArrayList<Delivery> deliveries     = iplTestDrive.csvDeliveryTest();
        System.out.println(iplTestDrive.economicalBowlers(deliveries, matches, "2015", 5));

    }
    public void yearFilterTest(ArrayList<Match> matches) {
        MatchFilter matchFilter = new YearMatchFilter();
        HashMap<String, LinkedList<Match>> hashMap = matchFilter.filter(matches);
        hashMap.forEach(new BiConsumer<String, LinkedList<Match>>() {
            @Override
            public void accept(String s, LinkedList<Match> matches) {
                System.out.println(s + " : " + matches.size());
            }
        });
    }
    public void wonFilterTest(ArrayList<Match> matches) {
        MatchFilter matchFilter = new WonMatchesPerTeamFilter();
        HashMap<String, LinkedList<Match>> hashMap = matchFilter.filter(matches);
        hashMap.forEach(new BiConsumer<String, LinkedList<Match>>() {
            @Override
            public void accept(String s, LinkedList<Match> matches) {
                System.out.println(s + " : " + matches.size());
            }
        });
    }
    public HashMap<String, Integer> extraRunFilterTest(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year) {
        ExtraConcededRunFilter deliveryFilter = new ExtraConcededRunFilter();
        HashMap<String, Integer> hashMap = deliveryFilter.filter(deliveries, matches,year);
        return hashMap;
    }
    public HashMap<String, Float> economicalBowlers(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year, int top) {
        EconomicalBowlers deliveryFilter = new EconomicalBowlers();
        HashMap<String, Float> hashMap = deliveryFilter.filter(deliveries, matches, year);
        return hashMap;
    }
    public ArrayList<Match> csvMatchTest() throws Exception {
        ArrayList<Match> matches;
        File matchData = new File("./data/matches.csv");
        CSVHelper csvHelper = new CSVHelper(matchData);
        matches = csvHelper.formatDataForMatches();
        return matches;
    }

    public ArrayList<Delivery> csvDeliveryTest() throws Exception {
        ArrayList<Delivery> deliveries;
        File matchData = new File("./data/deliveries.csv");
        CSVHelper csvHelper = new CSVHelper(matchData);
        deliveries = csvHelper.formatDataForDelivery();
        return deliveries;
    }
}
