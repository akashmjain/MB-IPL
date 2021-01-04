package com.akashmjain.ipl.filter.matchFilter;

import com.akashmjain.ipl.Match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class MatchFilter {
    public abstract HashMap<String, LinkedList<Match>> filter(ArrayList<Match> matches);

    protected LinkedList<Match> pushElementIntoLinkedList(Match match, String key,HashMap<String, LinkedList<Match>> hmap) {
        LinkedList<Match> linkedList = hmap.get(key) == null ? new LinkedList<>() : hmap.get(key);
        linkedList.add(match);
        return linkedList;
    }
}
