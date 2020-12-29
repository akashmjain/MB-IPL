package com.akashmjain.ipl.filter.matchFilter;

import com.akashmjain.ipl.Match;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class YearMatchFilter extends MatchFilter {
    HashMap<String, LinkedList<Match>> noOfMatchesPerYear = new HashMap<>();
    @Override
    public HashMap<String, LinkedList<Match>> filter(ArrayList<Match> matches) {
        for(Match match : matches ) {
            LinkedList<Match> linkedList = pushElementIntoLinkedList(match, match.getSeason(), noOfMatchesPerYear);
            noOfMatchesPerYear.put(match.getSeason(), linkedList);
        }
        return noOfMatchesPerYear;
    }

}
