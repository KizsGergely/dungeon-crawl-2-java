package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Environment implements Drawable {
    private Cell cell;
    private boolean isOpenDoor = false;

    public Environment(Cell cell) {
        this.cell = cell;
        this.cell.setEnvironment(this);
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

    public boolean isAnOpenDoor() {
        if (this instanceof CellarDoor) {
            if (((CellarDoor) this).isOpen()) {
                isOpenDoor = true;
            }
        } else if (this instanceof GardenDoor) {
            if (((GardenDoor) this).isOpen()) {
                isOpenDoor = true;
            }
        }
        return isOpenDoor;
    }
}
