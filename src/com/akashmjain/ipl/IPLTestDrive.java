package com.akashmjain.ipl;

import com.akashmjain.ipl.filter.FilterMatchInterface;
import com.akashmjain.ipl.filter.YearMatchFiler;

import java.io.File;
import java.util.*;
import java.util.function.BiConsumer;


public class IPLTestDrive {
    ArrayList<Match> matches;
    public static void main(String[] args) throws Exception {
        IPLTestDrive iplTestDrive = new IPLTestDrive();
        iplTestDrive.csvTest();
        iplTestDrive.filterTest();
    }
    public void filterTest() {
        FilterMatchInterface filterMatchInterface = new YearMatchFiler(matches);
        HashMap<String, LinkedList<Match>> hashMap = filterMatchInterface.filter();
        hashMap.forEach(new BiConsumer<String, LinkedList<Match>>() {
            @Override
            public void accept(String s, LinkedList<Match> matches) {
                System.out.println(s + " : " + matches.size());
            }
        });
//        Iterator hmIterator = hashMap.entrySet().iterator();
//        while(hmIterator.hasNext()) {
//            Map.Entry mapElement = (Map.Entry) hmIterator.next();
//
//            System.out.println(mapElement.getKey() + " : " + mapElement.getValue());
//        }
    }
    public void csvTest() throws Exception {
        File matchData = new File("./data/matches.csv");
        CSVHelper csvHelper = new CSVHelper(matchData);
        matches = csvHelper.formatDataForMatches();
//        for(Match match : matches) {
//            System.out.println(match);
//        }
    }
}
