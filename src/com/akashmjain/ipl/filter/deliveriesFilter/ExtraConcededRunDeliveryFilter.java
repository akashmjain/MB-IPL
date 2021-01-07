package com.akashmjain.ipl.filter.deliveriesFilter;

import com.akashmjain.ipl.Delivery;
import com.akashmjain.ipl.Match;

import java.util.ArrayList;
import java.util.HashMap;

public class ExtraConcededRunDeliveryFilter extends ParentDeliveryFilter {

    public HashMap<String, Integer> filter(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year) {
        ArrayList<Match> filteredByYear = filterByYear(matches, year);
        ArrayList<Delivery> filteredDelivery = filterDeliveryByMatchID(deliveries ,filteredByYear);

        return  teamViseRuns(filteredDelivery);
    }
    private HashMap<String, Integer> teamViseRuns(ArrayList<Delivery> deliveries) {
        HashMap<String, Integer> teamToRunHashMap = new HashMap<>();

        for(Delivery delivery : deliveries) {
            String key = delivery.getBattingTeam();
            int run = Integer.parseInt(delivery.getExtraRuns());
            Integer value = teamToRunHashMap.get(key) == null ? run : (teamToRunHashMap.get(key) + run);
            teamToRunHashMap.put(key, value);
        }
        return teamToRunHashMap;
    }


}
