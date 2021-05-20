package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class CellarDoor extends Environment {
    private boolean isOpen = false;

    public CellarDoor(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        if (isOpen) return "open cellar door";
        else return "closed cellar door";
    }

    public void setOpen() {
        isOpen = true;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
