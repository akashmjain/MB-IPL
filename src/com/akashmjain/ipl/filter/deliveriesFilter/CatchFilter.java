package com.akashmjain.ipl.filter.deliveriesFilter;

import com.akashmjain.ipl.Delivery;

import java.util.*;

public class CatchFilter {
    public HashMap<String,Integer> filter(ArrayList<Delivery> list) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        Integer noOfCatches;
        String fielder;

        for(Delivery delivery : list) {
            if(!delivery.getDismissalKind().equals("caught")) {
                continue;
            }
            fielder     = delivery.getFielder().equals("") ? "UN-KNOWN" : delivery.getFielder();
            noOfCatches = hashMap.get(fielder) == null ? 1 : hashMap.get(fielder) + 1;

            hashMap.put(fielder, noOfCatches);
        }
        return hashMap;
    }


    // sorting from high no of catches to low no of catches and returning top most results.
    public HashMap<String, Integer> getTop(HashMap<String, Integer> hm, int top)
    {
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        HashMap<String, Integer> topMost = new LinkedHashMap<String, Integer>();

        for (int i = 0; i < top; i++) {
            Map.Entry<String, Integer> aa = list.get(i);
            topMost.put(aa.getKey(), aa.getValue());
        }
        return topMost;
    }
}
