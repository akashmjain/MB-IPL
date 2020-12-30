package com.akashmjain.ipl.filter.deliveriesFilter;

import com.akashmjain.ipl.Delivery;
import com.akashmjain.ipl.Match;

import java.util.*;
import java.util.function.BiConsumer;

public class EconomicalBowlers extends DeliveryFilter{
    private class Boweler {
        private int run = 0;
        private int over = 0;
        private int totalBall = 0;
        private String name;
        private String lastOver;


        public int getRun() {
            return run;
        }

        public void setRun(int run) {
            this.run = run;
        }

        public int getOver() {
            return over;
        }

        public void incrementOver() {
            this.over++;
        }

        public int getTotalBall() {
            return totalBall;
        }

        public void incrementTotalBall() {
            totalBall++;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastOver() {
            return lastOver;
        }

        public void setLastOver(String lastOver) {
            this.lastOver = lastOver;
        }

        @Override
        public String toString() {
            return "Boweler{" +
                    "run=" + run +
                    ", over=" + over +
                    ", totalBall=" + totalBall +
                    ", name='" + name + '\'' +
                    ", lastOver='" + lastOver + '\'' +
                    '}';
        }
    }

    public HashMap<String, Float> filter(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year, int top) {
        ArrayList<Match> filteredByYear = filterByYear(matches, year);
        ArrayList<Delivery> filteredDelivery = filterDeliveryByMatchID(deliveries ,filteredByYear);
        HashMap<String, Float> hashMap = answer(filteredDelivery);
        hashMap = getTop(hashMap, top);
//        hashMap = getTop(hashMap, top);
        return hashMap;
    }



    public static HashMap<String, Float> getTop(HashMap<String, Float> hm, int top)
    {
        List<Map.Entry<String, Float> > list =
                new LinkedList<Map.Entry<String, Float> >(hm.entrySet());


        Collections.sort(list, new Comparator<Map.Entry<String, Float> >() {
            public int compare(Map.Entry<String, Float> o1,
                               Map.Entry<String, Float> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<String, Float> temp = new LinkedHashMap<String, Float>();
        for (int i = 0; i < top; i++) {
            Map.Entry<String, Float> aa = list.get(i);
            temp.put(aa.getKey(), aa.getValue());
        }
//        for (Map.Entry<String, Float> aa : list) {
//            temp.put(aa.getKey(), aa.getValue());
//        }
        return temp;
    }

    private HashMap<String, Float> answer(ArrayList<Delivery> filteredDelivery) {
        HashMap<String, Boweler> hashMap = new HashMap<>();
        for(Delivery delivery : filteredDelivery) {
            String key = delivery.getBowler();
            Boweler boweler = hashMap.get(key);
            String lastOverID = delivery.getMatch_id()+delivery.getOver(); // concat
            int run = Integer.parseInt(delivery.getTotal_runs());
            if(boweler == null) {
                String bowlerName =delivery.getBowler();
                boweler = new Boweler();
                boweler.setName(bowlerName);
                boweler.setRun(run);
                boweler.incrementOver();
                boweler.setLastOver(lastOverID);
            } else {
                if (!boweler.getLastOver().equals(lastOverID)) {
                    boweler.incrementOver();
                    boweler.setLastOver(lastOverID);
                }
                boweler.setRun(run + boweler.getRun());
            }
            boweler.incrementTotalBall();
            hashMap.put(key, boweler);
        }
        HashMap<String, Float> hm = new HashMap<>();
        hashMap.forEach(new BiConsumer<String, Boweler>() {
            @Override
            public void accept(String playerName, Boweler boweler) {
                float economyRate = ((float)boweler.getRun()) / ((float)boweler.getOver());
                hm.put(playerName, economyRate);

            }
        });
        return hm;
    }
}
