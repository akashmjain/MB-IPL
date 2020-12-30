package com.akashmjain.ipl.filter.deliveriesFilter;

import com.akashmjain.ipl.Delivery;
import com.akashmjain.ipl.Match;

import java.util.ArrayList;
import java.util.HashMap;

public class ExtraConcededRunFilter extends DeliveryFilter {
    public HashMap<String, Integer> filter(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year) {
        // filter the list for year 2017
        ArrayList<Match> filteredByYear = filterByYear(matches, year);
        ArrayList<Delivery> filteredDelivery = filterDeliveryByMatchID(deliveries ,filteredByYear);
        HashMap<String, Integer> hashMap = teamViseRuns(filteredDelivery);
        return hashMap;
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
