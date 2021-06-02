package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.logic.environment.*;

import java.io.Serializable;


public class Player extends Actor implements Serializable {
    private transient Inventory inventory = new Inventory();
    private boolean hasTorch = false;
    private boolean canPickupItem = false;
    private boolean isFighting = false;
    private boolean isKilledAMonster = false;
    private boolean hasCellarKey = false;
    private boolean hasGardenKey = false;
    private boolean isGrassCut = false;
    private String killedMonsterName;
    private transient Actor opponent;
    private int onLevel = 1;
    private int grassToCut;
    private boolean hasRing = false;
    private boolean isWifeHappy = false;
    private boolean isCatFed = false;

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
            if (!(nextCell.getType() == CellType.DOOR && !nextCell.getEnvironment().isAnOpenDoor())) { //if it's not a closed door
                if (nextCell.getEnvironment() instanceof StairDown) {
                    onLevel = 2;
                } else if (nextCell.getEnvironment() instanceof StairUp) {
                    onLevel = 1;
                }
                else {
                    nextCell.setActor(this);
                    if (nextCell.getItem() != null) {
                        canPickupItem = true;
                    }

                    cell.setActor(null);
                    cell = nextCell;
                    if (!isFighting) opponent = null;
                }
            }
        }
        mowTheLawn();
    }

    public void pickupItem() {
        Item item = cell.getItem();
        if (item instanceof Apple || item instanceof Pear) health += 4;
        else if (item instanceof Carrot) health += 3;
        else if (item instanceof Cheese || item instanceof Bread) health += 2;
        if (item instanceof CellarKey) inventory.pickupCellarKey();
        if (item instanceof GardenKey) inventory.pickupGardenKey();
        if (item instanceof Ring) hasRing = true;
        if (item instanceof Knife) attack += 3;
        if (inventory.hasCellarKey()) hasCellarKey = true;
        if (inventory.hasGardenKey()) hasGardenKey = true;
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
        boolean hasMeat = inventory.hasMeat();
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor monster = nextCell.getActor();
        if (monster != null && !(monster instanceof Player)) {
            if (monster instanceof Cat && hasMeat) {
                // cat won't hurt the player anymore and no fight occurs
                ((Cat) monster).reduceAttack();
                isCatFed = true;
                isFighting = false;
                isKilledAMonster = false;
            } else if (monster instanceof Wife && isGrassCut && isCatFed && hasRing) {
                // wife is happy
                isWifeHappy = true;
            } else {
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
            }
        } else {
            isFighting = false;
            isKilledAMonster = false;
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inv) {
        inventory = inv;
    }

    public boolean canPickup() {
        return canPickupItem;
    }

    public boolean isFighting() { return isFighting; }

    public void setGrassToCut(int grassNumber){
        grassToCut = grassNumber;
    }

    public Actor getOpponent() { return opponent; }

    public boolean isKilledAMonster() { return isKilledAMonster; }

    public String getKilledMonsterName() { return killedMonsterName; }

    public int getLevelNumber() {
        return onLevel;
    }

    public boolean isWifeHappy(){
        return isWifeHappy;
    }

    public void makeWifeHappy() {
        isWifeHappy = true;
    }

    public boolean hasCellarKey() {
        return hasCellarKey;
    }

    public boolean hasGardenKey() {
        return hasGardenKey;
    }

    public boolean isGrassCut() {
        return isGrassCut;
    }

    public int getGrassToCut() {
        return grassToCut;
    }

    public boolean getIsCatFed() {
        return isCatFed;
    }

    public boolean getHasTorch() {
        return hasTorch;
    }

    public void mowTheLawn() {
        Environment floor = cell.getEnvironment();
        if (floor instanceof Grass) {
            cell.setEnvironment(new CuttedGrass(cell));
            System.out.println("die fű DIE!");
            System.out.println(grassToCut);
            grassToCut -= 1;
            if (grassToCut == 0){
                isGrassCut = true;
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInventoryAsString() {
        return inventory.toString();
    }
}
