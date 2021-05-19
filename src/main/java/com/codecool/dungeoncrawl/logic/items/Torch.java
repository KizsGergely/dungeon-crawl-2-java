package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.environment.Environment;

public class Torch extends Item {
    public Torch(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "torch";
    }
}
