package com.akashmjain.ipl;

import com.akashmjain.ipl.filter.deliveriesFilter.EconomicalBowlers;
import com.akashmjain.ipl.filter.deliveriesFilter.ExtraConcededRunFilter;
import com.akashmjain.ipl.filter.matchFilter.MatchFilter;
import com.akashmjain.ipl.filter.matchFilter.WonMatchesPerTeamFilter;
import com.akashmjain.ipl.filter.matchFilter.YearMatchFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;


public class IPLTestDrive {

    public static void main(String[] args) throws Exception {
        IPLTestDrive        iplTestDrive   = new IPLTestDrive();
        ArrayList<Match>    matches        = iplTestDrive.csvMatchTest();
        ArrayList<Delivery> deliveries     = iplTestDrive.csvDeliveryTest();

        // Match Filter
        generateOutputFiles(iplTestDrive.yearFilterTest(matches), "yearFilter.dat");
        generateOutputFiles(iplTestDrive.wonFilterTest(matches), "wonFilter.dat");

        // Deliveries Filter
        System.out.println(iplTestDrive.extraRunFilterTest(deliveries, matches, "2015"));
        System.out.println(iplTestDrive.economicalBowlersFilter(deliveries, matches, "2015", 2));


    }

    private static void generateOutputFiles(HashMap<String, ?> list, String fileName) {
        try {
            File file = createFile(fileName);
            writeDataIntoFile(list, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDataIntoFile(HashMap<String, ?> list, File file) throws IOException{
        FileWriter fileWriter = new FileWriter(file);
        list.forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(String s, Object o) {
                try {
                    fileWriter.write(s + " : " + o.toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static File createFile(String fileName) throws IOException{
        File file = new File("./output/"+fileName);
        if(file.createNewFile()) {
            System.out.println("OUTPUT GENERATED AT LOCATION : " + file.getAbsolutePath());
        } else {
            System.out.println("OUTPUT FILE ALREADY EXIST OVERRIDING FILE: " + file.getAbsolutePath());

        }
        return file;
    }

    public HashMap<String, LinkedList<Match>> yearFilterTest(ArrayList<Match> matches) {
        MatchFilter matchFilter = new YearMatchFilter();
        HashMap<String, LinkedList<Match>> hashMap = matchFilter.filter(matches);
        return hashMap;
    }
    public HashMap<String, LinkedList<Match>> wonFilterTest(ArrayList<Match> matches) {
        MatchFilter matchFilter = new WonMatchesPerTeamFilter();
        HashMap<String, LinkedList<Match>> hashMap = matchFilter.filter(matches);
        return hashMap;
    }
    public HashMap<String, Integer> extraRunFilterTest(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year) {
        ExtraConcededRunFilter deliveryFilter = new ExtraConcededRunFilter();
        HashMap<String, Integer> hashMap = deliveryFilter.filter(deliveries, matches,year);
        return hashMap;
    }
    public HashMap<String, Float> economicalBowlersFilter(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year, int top) {
        EconomicalBowlers deliveryFilter = new EconomicalBowlers();
        HashMap<String, Float> hashMap = deliveryFilter.filter(deliveries, matches, year, top);
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
