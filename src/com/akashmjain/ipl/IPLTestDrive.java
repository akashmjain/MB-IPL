package com.akashmjain.ipl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class IPLTestDrive {

    public static void main(String[] args) throws Exception {
        File matchData = new File("./data/matches.csv");
        CSVHelper csvHelper = new CSVHelper(matchData);
        ArrayList<Match> matches = csvHelper.formatDataForMatches();
        for(Match match : matches) {
            System.out.println(match);
        }
    }
}
