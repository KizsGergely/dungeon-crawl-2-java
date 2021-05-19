package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Direction;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.Random;

public abstract class Actor implements Drawable {
    protected Random random = new Random();
    protected Direction direction;
    protected Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() != CellType.WALL &&
            nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void moveRandomly() {
        direction = Direction.values()[random.nextInt(4)];
        move(direction.getDx(), direction.getDy());
    }

    public int getHealth() {
        return health;
    }

    public void changeHealth(int dh) {
        this.health = health + dh;
    }

    public boolean checkIfDead() {
        return getHealth() <= 0;
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
}
