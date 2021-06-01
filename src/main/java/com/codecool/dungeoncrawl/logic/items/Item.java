package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.io.Serializable;

public abstract class Item implements Drawable, Serializable {
    private transient Cell cell;
    protected boolean isFood = false;

    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public boolean isFood() {
        return isFood;
    }
}
