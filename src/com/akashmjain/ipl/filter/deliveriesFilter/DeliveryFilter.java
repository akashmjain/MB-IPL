package com.akashmjain.ipl.filter.deliveriesFilter;

import com.akashmjain.ipl.Delivery;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class DeliveryFilter {
    public abstract HashMap<String, LinkedList<Delivery>> filter(ArrayList<Delivery> deliveries);

    protected LinkedList<Delivery> pushElementIntoLinkedList(Delivery delivery, String key,HashMap<String, LinkedList<Delivery>> hmap) {
        LinkedList<Delivery> linkedList = hmap.get(key);
        if(linkedList == null) {
            linkedList = new LinkedList<>();
        }
        linkedList.add(delivery);
        return linkedList;
    }
}

