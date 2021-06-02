package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;
    private int attack;
    private int defense;
    private int x;
    private int y;
    private boolean isCatFed;
    private boolean isGrassCut;
    private int grassToCut;
    private int onLevel;
    private String inventory;

    public PlayerModel(String playerName, int x, int y) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.hp = player.getHealth();
        this.attack = player.getAttack();
        this.defense = player.getDefense();
        this.x = player.getX();
        this.y = player.getY();
        this.isCatFed = player.getIsCatFed();
        this.isGrassCut = player.isGrassCut();
        this.grassToCut = player.getGrassToCut();
        this.onLevel = player.getLevelNumber();
        this.inventory = player.getInventoryAsString();
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isCatFed() {
        return isCatFed;
    }

    public void setCatFed(boolean catFed) {
        isCatFed = catFed;
    }

    public boolean isGrassCut() {
        return isGrassCut;
    }

    public void setGrassCut(boolean grassCut) {
        isGrassCut = grassCut;
    }

    public int getGrassToCut() {
        return grassToCut;
    }

    public void setGrassToCut(int grassToCut) {
        this.grassToCut = grassToCut;
    }

    public int getOnLevel() {
        return onLevel;
    }

    public void setOnLevel(int onLevel) {
        this.onLevel = onLevel;
    }

    public String getInventoryAsString() {
        return inventory;
    }

    public void setInventoryAsString(String inventory) {
        this.inventory = inventory;
    }
}
