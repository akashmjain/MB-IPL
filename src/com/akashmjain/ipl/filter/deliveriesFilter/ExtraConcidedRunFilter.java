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

    HashMap<String, Integer> hashMap = new HashMap<>();


    @Override
    public HashMap<String, Integer> filter(ArrayList<Delivery> list, ArrayList<Match> matches, String year) {
        // filter the list for year 2017
        HashMap<String, Integer> hm = filterByYear(matches, year);
        for(Delivery delivery : list) {
            String key = delivery.getMatch_id();
            Integer run = hm.get(key);
            if(run != null) {
                run += Integer.parseInt(delivery.getExtra_runs());
                hm.put(key, run);
            }
        }

        return hm;
    }

    private HashMap<String, Integer> filterByYear(ArrayList<Match> matches, String year) {
        HashMap<String, Integer> yearlySortedMatchHashMap = new HashMap<>();
        for(Match match : matches) {
            if(match.getSeason().equals(year)) {
                
                yearlySortedMatchHashMap.put(match.getId(), 0);
            }
        }
        return yearlySortedMatchHashMap;
    }
}
