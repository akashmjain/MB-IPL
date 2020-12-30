package com.akashmjain.ipl;

import com.akashmjain.ipl.filter.deliveriesFilter.DeliveryFilter;
import com.akashmjain.ipl.filter.deliveriesFilter.ExtraConcidedRunFilter;
import com.akashmjain.ipl.filter.matchFilter.MatchFilter;
import com.akashmjain.ipl.filter.matchFilter.WonMatchesPerTeamFilter;
import com.akashmjain.ipl.filter.matchFilter.YearMatchFilter;

import java.io.File;
import java.util.*;
import java.util.function.BiConsumer;


public class IPLTestDrive {

    public static void main(String[] args) throws Exception {
        IPLTestDrive        iplTestDrive   = new IPLTestDrive();
        ArrayList<Match>    matches        = iplTestDrive.csvTestMatch();
        ArrayList<Delivery> deliveries     = iplTestDrive.csvTestDelivery();
        iplTestDrive.extraRunFilter(deliveries, matches, "2016");
    }
    public void filterTest(ArrayList<Match> matches) {
        MatchFilter matchFilter = new YearMatchFilter();
        HashMap<String, LinkedList<Match>> hashMap = matchFilter.filter(matches);
        hashMap.forEach(new BiConsumer<String, LinkedList<Match>>() {
            @Override
            public void accept(String s, LinkedList<Match> matches) {
                System.out.println(s + " : " + matches.size());
            }
        });
    }
    public void filterTestWon(ArrayList<Match> matches) {
        MatchFilter matchFilter = new WonMatchesPerTeamFilter();
        HashMap<String, LinkedList<Match>> hashMap = matchFilter.filter(matches);
        hashMap.forEach(new BiConsumer<String, LinkedList<Match>>() {
            @Override
            public void accept(String s, LinkedList<Match> matches) {
                System.out.println(s + " : " + matches.size());
            }
        });
    }
    public void extraRunFilter(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year) {
        DeliveryFilter deliveryFilter = new ExtraConcidedRunFilter();
        HashMap<String, Integer> hashMap = deliveryFilter.filter(deliveries, matches,year);
        System.out.println(hashMap);
    }
    public ArrayList<Match> csvTestMatch() throws Exception {
        ArrayList<Match> matches;
        File matchData = new File("./data/matches.csv");
        CSVHelper csvHelper = new CSVHelper(matchData);
        matches = csvHelper.formatDataForMatches();
        return matches;
    }

    public ArrayList<Delivery> csvTestDelivery() throws Exception {
        ArrayList<Delivery> deliveries;
        File matchData = new File("./data/deliveries.csv");
        CSVHelper csvHelper = new CSVHelper(matchData);
        deliveries = csvHelper.formatDataForDelivery();
        return deliveries;
    }
}
