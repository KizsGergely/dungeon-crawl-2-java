package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class SpiderWeb extends Environment {
    public SpiderWeb(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "spiderweb";
    }
}
