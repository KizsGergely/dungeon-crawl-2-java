package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Inventory;

public class Player extends Actor {
    private Cell cell;
    private Inventory inventory = new Inventory();
    private boolean canPickupItem = false;

    public Player(Cell cell) {
        super(cell);
        this.cell = cell;
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void move(int dx, int dy) {
        canPickupItem = false;
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() != CellType.WALL &&
                nextCell.getActor() == null) {
            nextCell.setActor(this);
            if (nextCell.getItem() != null) {
                canPickupItem = true;
            }
            cell.setActor(null);
            cell = nextCell;
        }
    }

    public void pickupItem() {
        inventory.addItem(cell.getItem());
        cell.setItem(null);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean canPickup() {
        return canPickupItem;
    }
}
