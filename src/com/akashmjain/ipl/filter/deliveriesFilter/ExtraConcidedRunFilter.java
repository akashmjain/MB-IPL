package com.akashmjain.ipl.filter.deliveriesFilter;

import com.akashmjain.ipl.Delivery;
import com.akashmjain.ipl.Match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ExtraConcidedRunFilter implements DeliveryFilter {
    /*
     * algorithm :
     *  step 1:
     */




    @Override
    public HashMap<String, Integer> filter(ArrayList<Delivery> list, ArrayList<Match> matches, String year) {
        // filter the list for year 2017
        ArrayList<Match> filteredByYear = filterByYear(matches, year);
        ArrayList<Delivery> filteredDelivery = filterDeliveryByMatchID(list ,filteredByYear);
        HashMap<String, Integer> answer = getTheAnswer(filteredDelivery);
        return null;
    }
    private HashMap<String, Integer> getTheAnswer(ArrayList<Delivery> fd) {
            
        return null;
    }
    private ArrayList<Delivery> filterDeliveryByMatchID(ArrayList<Delivery> deliveries, ArrayList<Match> matches) {
        ArrayList<Delivery> sortedDeliveries = new ArrayList<>();
        for(Match match : matches) {
            for(Delivery delivery : deliveries) {
                if(match.getId().equals(delivery.getMatch_id())) {
                    sortedDeliveries.add(delivery);
                }
            }
        }
        return sortedDeliveries;
    }
    private ArrayList<Match> filterByYear(ArrayList<Match> matches, String year) {
        ArrayList<Match> yearlySortedMatchArray = new ArrayList<>();
        for(Match match : matches) {
            if(match.getSeason().equals(year)) {
                yearlySortedMatchArray.add(match);
            }
        }
        return yearlySortedMatchArray;
    }
}
