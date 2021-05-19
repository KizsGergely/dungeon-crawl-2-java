package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.environment.CuttedGrass;
import com.codecool.dungeoncrawl.logic.items.*;

public class Player extends Actor {
    private Inventory inventory = new Inventory();
    private boolean hasTorch = false;
    private boolean canPickupItem = false;
    private boolean isFighting = false;
    private boolean isKilledAMonster = false;
    private String killedMonsterName;
    private Actor opponent;

    public Player(Cell cell) {
        super(cell);
        name = "Player";
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
            if (!isFighting) opponent = null;
        }
    }

    public void pickupItem() {
        Item item = cell.getItem();
        if (item instanceof Apple || item instanceof Pear) health += 4;
        else if (item instanceof Carrot) health += 3;
        else if (item instanceof Cheese || item instanceof Bread) health += 2;
        if (item instanceof Torch) hasTorch = true;
        // if player collects food, it increases health but won't be in inventory
        if (!item.isFood()) {
            inventory.addItem(item);
        }
        if (item instanceof Apple || item instanceof Carrot || item instanceof Pear) {
            cell.setEnvironment(new CuttedGrass(cell));
        }
        cell.setItem(null);
    }

    public void attackIfEncounter(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor monster = nextCell.getActor();
        if (monster != null) {
            isFighting = true;
            opponent = monster;
            int hit = attack - monster.defense;
            if (hit < 0) hit = 0;
            monster.changeHealth(hit);
            // if monster is not dead yet, it will attack back
            if (!monster.checkIfDead()) {
                int hitBack = monster.attack - defense;
                if (hitBack <= 0) hitBack = 0;
                this.changeHealth(hitBack);
            } else {
                isKilledAMonster = true;
                killedMonsterName = monster.getTileName();
            }
        } else {
            isFighting = false;
            isKilledAMonster = false;
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean canPickup() {
        return canPickupItem;
    }

    public boolean isFighting() { return isFighting; }

    public Actor getOpponent() { return opponent; }

    public boolean isKilledAMonster() { return isKilledAMonster; }

    public String getKilledMonsterName() { return killedMonsterName; }

    public boolean hasTorch() {
        return hasTorch;
    }
}
