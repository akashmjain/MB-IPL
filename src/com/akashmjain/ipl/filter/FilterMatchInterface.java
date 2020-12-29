package com.akashmjain.ipl.filter;

import com.akashmjain.ipl.Match;

import java.util.HashMap;
import java.util.LinkedList;

public interface FilterMatchInterface {
    public HashMap<String, LinkedList<Match>> filter();
    // write a code to filter the matches per year
}
