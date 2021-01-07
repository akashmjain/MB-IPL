package com.akashmjain.ipl.filter.deliveriesFilter;

import com.akashmjain.ipl.Delivery;
import com.akashmjain.ipl.Match;

import java.util.*;
import java.util.function.BiConsumer;

public class EconomicalBowlersDeliveryFilter extends ParentDeliveryFilter {
    private class Bowler {
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
            return "Bowler{" +
                    "run=" + run +
                    ", over=" + over +
                    ", totalBall=" + totalBall +
                    ", name='" + name + '\'' +
                    ", lastOver='" + lastOver + '\'' +
                    '}';
        }
    }

    public HashMap<String, Float> filter(ArrayList<Delivery> deliveries, ArrayList<Match> matches, String year) {
        ArrayList<Match> filteredByYear = filterByYear(matches, year);
        ArrayList<Delivery> filteredDelivery = filterDeliveryByMatchID(deliveries ,filteredByYear);
        HashMap<String, Float> hashMap = calculateEconomicRate(filteredDelivery);
        return hashMap;
    }



    public HashMap<String, Float> getTop(HashMap<String, Float> hm, int top)
    {
        List<Map.Entry<String, Float> > list = new LinkedList<Map.Entry<String, Float> >(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Float> >() {
            public int compare(Map.Entry<String, Float> o1,
                               Map.Entry<String, Float> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<String, Float> sortedHashMap = new LinkedHashMap<String, Float>();
        for (int i = 0; i < top; i++) {
            Map.Entry<String, Float> aa = list.get(i);
            sortedHashMap.put(aa.getKey(), aa.getValue());
        }
        return sortedHashMap;
    }

    private HashMap<String, Float> calculateEconomicRate(ArrayList<Delivery> filteredDelivery) {
        HashMap<String, Bowler> bowlerHashMap = getBowlerMap(filteredDelivery);
        return getPlayerEconomyMap(bowlerHashMap);
    }

    private HashMap<String, Float> getPlayerEconomyMap(HashMap<String, Bowler> bowlerHashMap) {
        HashMap<String, Float> playerEconomyMap = new HashMap<>();
        bowlerHashMap.forEach(new BiConsumer<String, Bowler>() {
            @Override
            public void accept(String playerName, Bowler bowler) {
                float economyRate = ((float) bowler.getRun()) / ((float) bowler.getOver());
                playerEconomyMap.put(playerName, economyRate);
            }
        });
        return playerEconomyMap;
    }

    private HashMap<String, Bowler> getBowlerMap(ArrayList<Delivery> deliveries) {
        HashMap<String, Bowler> bowlerHashMap = new HashMap<>();
        for(Delivery delivery : deliveries) {
            int run           = Integer.parseInt(delivery.getTotalRuns());
            String key        = delivery.getBowler();
            String lastOverID = delivery.getMatchId()+delivery.getOver(); // concatenated ID
            Bowler bowler     = bowlerHashMap.get(key);

            if(bowler == null) {
                String bowlerName =delivery.getBowler();
                bowler = new Bowler();
                bowler.setName(bowlerName);
                bowler.setRun(run);
                bowler.incrementOver();
                bowler.setLastOver(lastOverID);
            } else {
                if (!bowler.getLastOver().equals(lastOverID)) {
                    bowler.incrementOver();
                    bowler.setLastOver(lastOverID);
                }
                bowler.setRun(run + bowler.getRun());
            }

            bowler.incrementTotalBall();
            bowlerHashMap.put(key, bowler);
        }
        return bowlerHashMap;
    }
}
