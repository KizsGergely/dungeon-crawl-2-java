package com.codecool.dungeoncrawl.logic.items;

import java.io.Serializable;
import java.util.*;

public class Inventory implements Serializable {
    private HashMap<String, Integer> inventory = new HashMap<>();
    private boolean hasCellarKey = false;
    private boolean hasGardenKey = false;

    public Inventory() {
    }

    public Inventory(HashMap inventoryHashMap) {

        this.inventory = inventoryHashMap;
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

    public boolean checkCellarKey() {
        for (Map.Entry<String, Integer> item: inventory.entrySet()) {
            if (item.getKey().equals("cellar key")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkGardenKey() {
        for (Map.Entry<String, Integer> item: inventory.entrySet()) {
            if (item.getKey().equals("garden key")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfHasTorch() {
        for (Map.Entry<String, Integer> item: inventory.entrySet()) {
            if (item.getKey().equals("torch")) {
                return true;
            }
        }
        return false;
    }

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

    public HashMap<String, Integer> generateInventory(String inventoryString) {
        HashMap<String, Integer> inventoryHashmap  = new HashMap<>();
        String[] inventoryAsList = inventoryString.split("\n");
        if (!inventoryString.equals("")){
            for (String substring: inventoryAsList) {
                substring = substring.replace("- ", "");
                String[] substringParts = substring.split(": ");
                inventoryHashmap.put(substringParts[0], Integer.parseInt(substringParts[1]));
            }
        }

        return inventoryHashmap;
    }
}
