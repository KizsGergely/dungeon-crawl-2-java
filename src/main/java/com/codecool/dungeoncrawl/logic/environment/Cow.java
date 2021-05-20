package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Cow extends Environment {
    public Cow(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "cow";
    }
}
