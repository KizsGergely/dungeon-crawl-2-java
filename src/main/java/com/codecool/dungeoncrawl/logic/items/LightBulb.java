package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class LightBulb extends Item {
    public LightBulb(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "light bulb";
    }
}
