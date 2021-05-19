package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Tv extends Environment {
    public Tv(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "tv";
    }
}
