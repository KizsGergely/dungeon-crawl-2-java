package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Knife extends Item {
    public Knife(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "knife";
    }
}
