package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Apple extends Item {
    public Apple(Cell cell) {
        super(cell);
        isFood = true;
    }

    public String getTileName() {
        return "apple";
    }

}
