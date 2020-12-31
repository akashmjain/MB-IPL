package com.akashmjain.ipl.filter.matchFilter;

import com.akashmjain.ipl.Match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class WonMatchesPerTeamFilter extends MatchFilter {
    HashMap<String, LinkedList<Match>> noOfMatchesWonPerTeam = new HashMap<>();
    @Override
    public HashMap<String, LinkedList<Match>> filter(ArrayList<Match> matches) {

        for(Match match : matches) {
            LinkedList<Match> linkedList = pushElementIntoLinkedList(match, match.getWinner(),noOfMatchesWonPerTeam);
            String winner = match.getWinner().equals("") ? "NO WINNER" : match.getWinner();
            noOfMatchesWonPerTeam.put(winner, linkedList);
        }
        return noOfMatchesWonPerTeam;
    }

}
