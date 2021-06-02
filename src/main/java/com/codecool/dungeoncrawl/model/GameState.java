package com.codecool.dungeoncrawl.model;

import java.sql.Date;

public class GameState extends BaseModel {
    private Date savedAt;
    private String currentMap;
    private String otherMap;
    private PlayerModel player;
    private String saveName;

    public GameState(String currentMap, String otherMap, Date savedAt, PlayerModel player, String saveName) {
        this.currentMap = currentMap;
        this.otherMap = otherMap;
        this.savedAt = savedAt;
        this.player = player;
        this.saveName = saveName;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }

    public String getOtherMap() {
        return otherMap;
    }

    public void setOtherMap(String otherMap) {
        this.otherMap = otherMap;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
}
