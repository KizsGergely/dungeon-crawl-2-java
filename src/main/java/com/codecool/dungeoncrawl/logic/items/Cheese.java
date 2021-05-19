package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Cheese extends Item {
    public Cheese(Cell cell) {
        super(cell);
        isFood = true;
    }

    public String getTileName() {
        return "cheese";
    }
}
