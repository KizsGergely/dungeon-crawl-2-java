package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class OpenDoor extends Environment {
    public OpenDoor(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "open door";
    }
}
