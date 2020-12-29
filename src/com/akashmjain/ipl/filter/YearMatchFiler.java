package com.akashmjain.ipl.filter;

import com.akashmjain.ipl.Match;
import org.jetbrains.annotations.TestOnly;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class YearMatchFiler implements FilterMatchInterface {
    ArrayList<Match> matches = new ArrayList<>();
    HashMap<String, LinkedList<Match>> noOfMatchesPerYear = new HashMap<>();

    public YearMatchFiler(ArrayList<Match> matches) {
        this.matches = matches;
    }
    @Override
    public HashMap<String, LinkedList<Match>> filter() {
        // use hash match

        for(Match match : matches ) {
            LinkedList<Match> linkedList = pushElementIntoLinkedList(match, noOfMatchesPerYear);
            noOfMatchesPerYear.put(match.getSeason(), linkedList);
        }
        return noOfMatchesPerYear;
    }

    private LinkedList<Match> pushElementIntoLinkedList(Match match, HashMap<String, LinkedList<Match>> hmap) {
        LinkedList<Match> linkedList = hmap.get(match.getSeason());

        if(linkedList == null) {
            linkedList = new LinkedList<>();
        }

        linkedList.add(match);
        return linkedList;
    }
    @TestOnly
    public static void main(String[] args) {

    }
}
