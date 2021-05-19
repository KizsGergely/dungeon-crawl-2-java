package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class CuttedGrass extends Environment {
    public CuttedGrass(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "cutted grass";
    }
}
