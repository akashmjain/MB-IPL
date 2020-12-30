package com.akashmjain.ipl.filter.deliveriesFilter;

import com.akashmjain.ipl.Delivery;
import com.akashmjain.ipl.Match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ExtraConcidedRunFilter implements DeliveryFilter {
    @Override
    public HashMap<String, Integer> filter(ArrayList<Delivery> list, ArrayList<Match> matches, String year) {
        // filter the list for year 2017
        ArrayList<Match> filteredByYear = filterByYear(matches, year);
        ArrayList<Delivery> filteredDelivery = filterDeliveryByMatchID(list ,filteredByYear);
        HashMap<String, Integer> hashMap = teamViseRuns(filteredDelivery);
        return hashMap;
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

    private HashMap<String, Integer> teamViseRuns(ArrayList<Delivery> fd) {
        HashMap<String, Integer> answer = new HashMap<>();
        for(Delivery d: fd) {
            String key = d.getBatting_team();
            Integer value = answer.get(key);
            int run = Integer.parseInt(d.getExtra_runs());
            if(value == null) {
                value = run;
            } else {
                value += run;
            }
            answer.put(key, value);
        }
        return answer;
    }


}
