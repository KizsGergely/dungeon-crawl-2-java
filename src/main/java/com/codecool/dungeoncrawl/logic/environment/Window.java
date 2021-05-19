package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Window extends Environment {
    public Window(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "window";
    }
}
