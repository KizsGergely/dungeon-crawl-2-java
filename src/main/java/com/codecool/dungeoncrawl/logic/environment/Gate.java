package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Gate extends Environment {
    public Gate(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "gate";
    }
}
