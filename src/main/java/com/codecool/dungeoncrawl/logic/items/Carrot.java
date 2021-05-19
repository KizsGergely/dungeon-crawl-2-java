package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Carrot extends Item {
    public Carrot(Cell cell) {
        super(cell);
        isFood = true;
    }

    public String getTileName() {
        return "carrot";
    }
}
