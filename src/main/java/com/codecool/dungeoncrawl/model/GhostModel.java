package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class GhostModel extends BaseModel{
    private String playerName;
    private int hp;
    private int attack;
    private int defense;
    private int x;
    private int y;

    public GhostModel(String playerName, int x, int y) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
    }

    public GhostModel(Player player) {
        this.playerName = player.getName();
        this.x = player.getX();
        this.y = player.getY();

        this.hp = player.getHealth();

    }

    public String getGhostName() {
        return playerName;
    }

    public void setGhostName(String playerName) {
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
        return y;
    }

    public void setAttack(int y) {
        this.y = y;
    }
    public int getDefense() {
        return y;
    }

    public void setDefense(int y) {
        this.y = y;
    }

}
