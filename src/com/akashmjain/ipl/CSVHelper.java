package com.akashmjain.ipl;

import org.jetbrains.annotations.TestOnly;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVHelper {
    private File file;

    public CSVHelper(File file) throws Exception{
        if(file.canRead()) {
            this.file = file;
        } else {
            throw new Exception("Please provide proper File Name");
        }
    }

    public ArrayList<Match> formatDataForMatches() {
        ArrayList<Match> matches = new ArrayList<>();
        ArrayList<String> lines =  separateLines();
        for(String line : lines) {
            matches.add(createMatchObject(line));
        }
        return matches;

    }

    private ArrayList<String> separateLines() {
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
    private ArrayList<String> readTillCommaAndWhateverYouReadPlaceItIntoAStringArray(String line) {

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
        if(line.charAt(line.length() - 1) == ',') {
            list.add("");
        }

        return list;
    }
    private Match createMatchObject(String matchTuple) {

        ArrayList<String> list =  readTillCommaAndWhateverYouReadPlaceItIntoAStringArray(matchTuple);

        String id                   = list.get(0);
        String season               = list.get(1);
        String city                 = list.get(2);
        String date                 = list.get(3);
        String team1                = list.get(4);
        String team2                = list.get(5);
        String toss_winner          = list.get(6);
        String toss_decision        = list.get(7);
        String result               = list.get(8);
        String dl_applied           = list.get(9);
        String winner               = list.get(10);
        String win_by_runs          = list.get(11);
        String win_by_wickets       = list.get(12);
        String player_of_match      = list.get(13);
        String venue                = list.get(14);
        String umpire1              = list.get(15);
        String umpire2              = list.get(16);
        String umpire3              = list.get(17);

        return new Match(
            id,
            season,
            city,
            date,
            team1,
            team2,
            toss_winner,
            toss_decision,
            result,
            dl_applied,
            winner,
            win_by_runs,
            win_by_wickets,
            player_of_match,
            venue,
            umpire1,
            umpire2,
            umpire3
        );
    }
    @TestOnly
    public static void main(String[] args) throws Exception {
        CSVHelper csvHelper = new CSVHelper(new File("./data/matches.small.csv"));

        String data = "id,season,city,date,team1,team2,toss_winner,toss_decision,result,dl_applied,winner,win_by_runs,win_by_wickets,player_of_match,venue,umpire1,umpire2,umpire3";
        System.out.println(csvHelper.readTillCommaAndWhateverYouReadPlaceItIntoAStringArray(data));
    }
}
