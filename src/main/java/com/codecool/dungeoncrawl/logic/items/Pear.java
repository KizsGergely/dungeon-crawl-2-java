package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Pear extends Item {
    public Pear(Cell cell) {
        super(cell);
        isFood = true;
    }

    public String getTileName() {
        return "pear";
    }
}
