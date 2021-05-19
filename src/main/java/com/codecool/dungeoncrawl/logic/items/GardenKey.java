package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class GardenKey extends Item {
    public GardenKey(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "garden key";
    }
}
