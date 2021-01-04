package com.akashmjain.ipl.filter.deliveriesFilter;

import com.akashmjain.ipl.Delivery;
import com.akashmjain.ipl.Match;

import java.util.ArrayList;
import java.util.HashMap;

public class ExtraConcededRunFilter extends DeliveryParentFilter {

    public HashMap<String, Integer> filter(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year) {
        ArrayList<Match> filteredByYear = filterByYear(matches, year);
        ArrayList<Delivery> filteredDelivery = filterDeliveryByMatchID(deliveries ,filteredByYear);
        HashMap<String, Integer> hashMap = teamViseRuns(filteredDelivery);
        return hashMap;
    }
    private HashMap<String, Integer> teamViseRuns(ArrayList<Delivery> deliveries) {
        HashMap<String, Integer> answer = new HashMap<>();

        for(Delivery delivery : deliveries) {
            String key = delivery.getBatting_team();
            int run = Integer.parseInt(delivery.getExtra_runs());

            Integer value = answer.get(key) == null ? run : (answer.get(key) + run);
            answer.put(key, value);
        }
        return answer;
    }


}
