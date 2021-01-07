package com.akashmjain.ipl.filter.deliveriesFilter;

import com.akashmjain.ipl.Delivery;
import com.akashmjain.ipl.Match;


import java.util.ArrayList;

public class ParentDeliveryFilter {
    protected ArrayList<Match> filterByYear(ArrayList<Match> matches, String year) {
        ArrayList<Match> yearlySortedMatchArray = new ArrayList<>();
        for(Match match : matches) {
            if(match.getSeason().equals(year)) {
                yearlySortedMatchArray.add(match);
            }
        }
        return yearlySortedMatchArray;
    }
    protected ArrayList<Delivery> filterDeliveryByMatchID(ArrayList<Delivery> deliveries, ArrayList<Match> matches) {
        ArrayList<Delivery> sortedDeliveries = new ArrayList<>();
        for(Match match : matches) {
            for(Delivery delivery : deliveries) {
                if(match.getId().equals(delivery.getMatchId())) {
                    sortedDeliveries.add(delivery);
                }
            }
        }
        return sortedDeliveries;
    }
}

