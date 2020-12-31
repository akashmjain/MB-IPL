package com.akashmjain.ipl.filter.matchFilter;

import com.akashmjain.ipl.Match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public interface MatchFilterInterface {
    public  HashMap<String, LinkedList<Match>> filter(ArrayList<Match> matches);
}
