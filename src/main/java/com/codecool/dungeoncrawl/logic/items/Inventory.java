package com.codecool.dungeoncrawl.logic.items;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private HashMap<String, Integer> inventory = new HashMap<>();
    private boolean hasCellarKey = false;
    private boolean hasGardenKey = false;

    public Inventory() {
    }

    public void addItem(Item item) {
        String key = item.getTileName();
        if (inventory.containsKey(key))
            inventory.put(key, inventory.get(key) + 1);
        else
            inventory.put(key, 1);
    }
    public boolean hasMeat() {
        for (Map.Entry<String, Integer> item: inventory.entrySet()) {
            if (item.getKey().equals("meat")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasBulb() {
        for (Map.Entry<String, Integer> item: inventory.entrySet()) {
            if (item.getKey().equals("light bulb")) {
                return true;
            }
        }
        return false;
    }
    public boolean hasRing() {
        for (Map.Entry<String, Integer> item: inventory.entrySet()) {
            if (item.getKey().equals("Ring")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCellarKey() {
        return hasCellarKey;
    }

    public boolean hasGardenKey() { return hasGardenKey; }

    public void pickupCellarKey() { hasCellarKey = true; }

    public void pickupGardenKey() { hasGardenKey = true; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String item: inventory.keySet()) {
            sb.append("- ").append(item).append(": ").append(inventory.get(item)).append("\n");
        }
        return sb.toString();
    }
}
