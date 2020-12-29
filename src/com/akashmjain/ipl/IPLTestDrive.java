package com.akashmjain.ipl;

import com.akashmjain.ipl.filter.matchFilter.MatchFilter;
import com.akashmjain.ipl.filter.matchFilter.WonMatchesPerTeamFilter;
import com.akashmjain.ipl.filter.matchFilter.YearMatchFilter;

import java.io.File;
import java.util.*;
import java.util.function.BiConsumer;


public class IPLTestDrive {
    ArrayList<Match> matches;
    ArrayList<Delivery> deliveries;
    public static void main(String[] args) throws Exception {
        IPLTestDrive iplTestDrive = new IPLTestDrive();
        iplTestDrive.csvTestDelivery();
//        iplTestDrive.csvTestMatch();
//        iplTestDrive.filterTestWon();
    }
    public void filterTest() {
        MatchFilter matchFilter = new YearMatchFilter();
        HashMap<String, LinkedList<Match>> hashMap = matchFilter.filter(matches);
        hashMap.forEach(new BiConsumer<String, LinkedList<Match>>() {
            @Override
            public void accept(String s, LinkedList<Match> matches) {
                System.out.println(s + " : " + matches.size());
            }
        });
    }
    public void filterTestWon() {
        MatchFilter matchFilter = new WonMatchesPerTeamFilter();
        HashMap<String, LinkedList<Match>> hashMap = matchFilter.filter(matches);
        hashMap.forEach(new BiConsumer<String, LinkedList<Match>>() {
            @Override
            public void accept(String s, LinkedList<Match> matches) {
                System.out.println(s + " : " + matches.size());
            }
        });
    }
    public void csvTestMatch() throws Exception {
        File matchData = new File("./data/matches.csv");
        CSVHelper csvHelper = new CSVHelper(matchData);
        matches = csvHelper.formatDataForMatches();
        for(Match match : matches) {
            System.out.println(match);
        }
    }
    public void csvTestDelivery() throws Exception {
        File matchData = new File("./data/deliveries.csv");
        CSVHelper csvHelper = new CSVHelper(matchData);
        deliveries = csvHelper.formatDataForDelivery();
        for(Delivery delivery : deliveries) {
            System.out.println(delivery);
        }
    }
}
