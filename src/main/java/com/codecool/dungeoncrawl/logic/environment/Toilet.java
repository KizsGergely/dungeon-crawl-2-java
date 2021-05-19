package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Toilet extends Environment {
    public Toilet(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "toilet";
    }
}
