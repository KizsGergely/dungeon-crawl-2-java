package com.codecool.dungeoncrawl.logic.items;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, Integer> inventory = new HashMap<>();
    private Key key;

    public Inventory() {
    }

    public void addItem(Item item) {
        String key = item.getTileName();
        if (inventory.containsKey(key))
            inventory.put(key, inventory.get(key) + 1);
        else
            inventory.put(key, 1);
    }

    public boolean hasKey() {
        return key != null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String item: inventory.keySet()) {
            sb.append("- ").append(item).append(": ").append(inventory.get(item)).append("\n");
        }
        return sb.toString();
    }
}