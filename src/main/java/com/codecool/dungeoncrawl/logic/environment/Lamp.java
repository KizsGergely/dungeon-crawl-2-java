package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Lamp extends Environment {
    public Lamp(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "lamp";
    }
}
