package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Carrot extends Item {
    public Carrot(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "carrot";
    }
}
