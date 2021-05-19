package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class ClosedDoor extends Environment {
    public ClosedDoor(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "closed door";
    }
}
