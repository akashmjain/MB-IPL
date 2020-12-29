package com.akashmjain.ipl.filter.deliveriesFilter;

import com.akashmjain.ipl.Delivery;
import com.akashmjain.ipl.Match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ExtraConcidedRunFilter {
    /*
     * algorithm :
     *  step 1:
     */
    // @TODO refactor following code
    HashMap<String, Integer> hashMap = new HashMap<>();
    public HashMap<String, Integer> filter(ArrayList<Delivery> list) {
        int extraRun;
        for(Delivery delivery : list) {
            String key = delivery.getBatting_team();
            try {
                extraRun = Integer.parseInt(delivery.getExtra_runs());
            } catch (NumberFormatException e) {
                extraRun = 0;
            }

            if(hashMap.get(key) == null) {
                hashMap.put(key, extraRun);
            } else {
                extraRun += hashMap.get(key);
                hashMap.put(key, extraRun);
            }
        }
        return hashMap;
    }

}
