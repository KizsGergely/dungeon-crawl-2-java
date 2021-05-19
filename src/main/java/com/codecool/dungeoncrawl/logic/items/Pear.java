package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Pear extends Item {
    public Pear(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "pear";
    }
}
