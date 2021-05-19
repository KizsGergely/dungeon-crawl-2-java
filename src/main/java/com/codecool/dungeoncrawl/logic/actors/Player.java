package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.environment.StairDown;
import com.codecool.dungeoncrawl.logic.environment.StairUp;
import com.codecool.dungeoncrawl.logic.items.*;

public class Player extends Actor {
    private Inventory inventory = new Inventory();
    private boolean canPickupItem = false;
    private boolean isFighting = false;
    private boolean isKilledAMonster = false;
    private boolean canGoToCellar = false;
    private boolean canGoOut = false;
    private boolean hasCellarKey = false;
    private boolean hasGardenKey = false;
    private String killedMonsterName;
    private Actor opponent;
    private int onLevel = 1;

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
        if (nextCell.getType() != CellType.WALL && nextCell.getActor() == null) {
            if (!(nextCell.getType() == CellType.DOOR && !nextCell.getEnvironment().isAnOpenDoor())) {
                nextCell.setActor(this);
                if (nextCell.getItem() != null) {
                    canPickupItem = true;
                }
                if (nextCell.getEnvironment() instanceof StairDown) {
                    onLevel = 2;
                    canGoToCellar = true;
                } else if (nextCell.getEnvironment() instanceof StairUp) {
                    onLevel = 1;
                }
                cell.setActor(null);
                cell = nextCell;
                if (!isFighting) opponent = null;
            }
        }
    }

    public void pickupItem() {
        if (cell.getItem() instanceof Sword) attack += 3;
        if (cell.getItem() instanceof Cheese) health += 2;
        if (cell.getItem() instanceof CellarKey) inventory.pickupCellarKey();
        if (cell.getItem() instanceof GardenKey) inventory.pickupGardenKey();
        inventory.addItem(cell.getItem());
        if (inventory.hasCellarKey()) hasCellarKey = true;
        if (inventory.hasGardenKey()) hasGardenKey = true;
        cell.setItem(null);
    }

    public void attackIfEncounter(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor monster = nextCell.getActor();
        if (monster != null) {
            isFighting = true;
            opponent = monster;
            int hit = monster.defense - attack;
            if (hit < 0) hit = Math.abs(hit);
            monster.changeHealth(-hit);
            // if monster is not dead yet, it will attack back
            if (!monster.checkIfDead()) {
                int hitBack = monster.attack - defense;
                if (hitBack <= 0) hitBack = 0;
                this.changeHealth(-hitBack);
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

    public boolean canGoToCellar() {  //
        return canGoToCellar;
    }

    public int getLevelNumber() {
        return onLevel;
    }

    public boolean hasCellarKey() {
        return hasCellarKey;
    }

    public boolean hasGardenKey() {
        return hasGardenKey;
    }
}
