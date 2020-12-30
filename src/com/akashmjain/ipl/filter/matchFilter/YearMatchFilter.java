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
            String key = match.getSeason();
            LinkedList<Match> linkedList = pushElementIntoLinkedList(match, key, noOfMatchesPerYear);
            noOfMatchesPerYear.put(key, linkedList);
        }
        return noOfMatchesPerYear;
    }

}
