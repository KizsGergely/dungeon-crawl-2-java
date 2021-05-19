package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class CellarKey extends Item {
    public CellarKey(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "cellar key";
    }
}
