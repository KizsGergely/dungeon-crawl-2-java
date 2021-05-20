package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class GardenDoor extends Environment {
    private boolean isOpen = false;

    public GardenDoor(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        if (isOpen) return "open garden door";
        else return "closed garden door";
    }

    public void setOpen() {
        isOpen = true;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
