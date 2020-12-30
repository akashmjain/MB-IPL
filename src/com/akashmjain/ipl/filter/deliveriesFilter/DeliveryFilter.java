package com.akashmjain.ipl.filter.deliveriesFilter;

import com.akashmjain.ipl.Delivery;
import com.akashmjain.ipl.Match;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public interface DeliveryFilter {
    public HashMap<String, Integer> filter(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year);
}

