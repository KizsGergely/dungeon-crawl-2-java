package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Direction;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.io.Serializable;
import java.util.Random;

public abstract class Actor implements Drawable, Serializable {
    protected Random random = new Random();
    protected Direction direction;
    protected Cell cell;
    protected String name;
    protected int health = 10;
    protected int attack = 5;
    protected int defense = 2;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    protected Actor() {
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
    public void moveActors(int dx, int dy, int width, int height) {
        int currentX = this.getX();
        int currentY = this.getY();
        if (currentX + dx < width &&
                currentX + dx >= 0 &&
                currentY + dy < height &&
                currentY + dy >= 0) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (nextCell.getActor() == null) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }
        }
    }

    public void moveRandomly() {
        direction = Direction.values()[random.nextInt(4)];
        move(direction.getDx(), direction.getDy());
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void changeHealth(int dh) {
        this.health = health - dh;
    }

    public boolean checkIfDead() {
        return getHealth() <= 0;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void setX(int position) { cell.setX(position); }

    public void setY(int position) { cell.setY(position); }
}
