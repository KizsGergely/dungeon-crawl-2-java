package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bread extends Item {
    public Bread(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "bread";
    }
}
