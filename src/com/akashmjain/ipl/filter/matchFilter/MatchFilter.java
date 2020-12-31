package com.akashmjain.ipl.filter.matchFilter;

import com.akashmjain.ipl.Match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class MatchFilter implements MatchFilterInterface {

    protected LinkedList<Match> pushElementIntoLinkedList(Match match, String key,HashMap<String, LinkedList<Match>> hmap) {
        LinkedList<Match> linkedList = hmap.get(key);
        if(linkedList == null) {
            linkedList = new LinkedList<>();
        }
        linkedList.add(match);
        return linkedList;
    }
}
