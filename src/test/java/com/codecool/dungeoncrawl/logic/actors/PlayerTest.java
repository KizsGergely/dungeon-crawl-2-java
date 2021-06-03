package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.environment.CellarDoor;
import com.codecool.dungeoncrawl.logic.environment.StairDown;
import com.codecool.dungeoncrawl.logic.items.CellarKey;
import com.codecool.dungeoncrawl.logic.items.Torch;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    static Cell cell = null;
    static GameMap gameMap = null;
    Player player = null;

    @BeforeAll
    public static void setUp (){
        gameMap = new GameMap(25,20, CellType.EMPTY);
        cell = new Cell(gameMap,20,4, CellType.EMPTY);
    }


    @Test
    public void ifPlayerIsReallyItself(){

        player = new Player(gameMap.getCell(20, 4));
        cell.setActor(player);
        assertEquals("player", gameMap.getCell(20, 4).getActor().getTileName());
    }

    @Test
    public void isThereWallCollision(){
        gameMap.getCell(20,5).setType(CellType.WALL);
        player = new Player(gameMap.getCell(20, 4));
        player.move(0,1);
        assertEquals(20, player.getX());
        assertEquals(4, player.getY());

    }

    @Test
    public void isThereDoorCollision(){
        cell.setEnvironment(new CellarDoor(gameMap.getCell(20,5)));
        gameMap.getCell(20,5).setType(CellType.DOOR);
        player = new Player(gameMap.getCell(20, 4));
        player.move(0,1);
        assertEquals(20, player.getX());
        assertEquals(4, player.getY());

    }

    @Test
    public void canStepToStairDown() {
        cell.setEnvironment(new StairDown(gameMap.getCell(20,5)));
        gameMap.getCell(20,5).setType(CellType.STAIR);
        player = new Player(gameMap.getCell(20, 4));
        player.move(0,1);
        assertEquals(20, player.getX());
        assertEquals(4, player.getY());

    }

    @Test
    public void canChangeMap() {
        gameMap = new GameMap(25,20,CellType.FLOOR);
        cell.setEnvironment(new StairDown(gameMap.getCell(20,5)));
        gameMap.getCell(20,5).setType(CellType.STAIR);
        player = new Player(gameMap.getCell(20, 4));
        player.move(0,1);
        assertEquals(2,player.getLevelNumber());
    }

    @Test
    public void canPickUpKey() {
        gameMap = new GameMap(25,20,CellType.FLOOR);
        cell.setItem(new CellarKey(gameMap.getCell(20, 5)));
        gameMap.getCell(20,5).setType(CellType.FLOOR);
        player = new Player(gameMap.getCell(20, 4));
        player.move(0,1);
        player.pickupItem();
        assertTrue(player.getInventory().hasCellarKey());
    }

    @Test
    public void canOpenCellarDoor() {
        CellarDoor cellardoor = new CellarDoor(gameMap.getCell(20,5));
        cell.setEnvironment(cellardoor);
        gameMap.getCell(20,5).setType(CellType.DOOR);
        player = new Player(gameMap.getCell(20,4));
        cell.setItem(new CellarKey(gameMap.getCell(20, 4)));
        player.pickupItem();
        if (player.hasCellarKey()) {
            cellardoor.setOpen();
        }
        player.move(0,1);
        assertEquals(20, player.getX());
        assertEquals(5, player.getY());
    }

    @Test
    public void cannotOpenCellarDoorWithTorch() {
        CellarDoor cellardoor = new CellarDoor(gameMap.getCell(20,5));
        cell.setEnvironment(cellardoor);
        gameMap.getCell(20,5).setType(CellType.DOOR);
        player = new Player(gameMap.getCell(20,4));
        cell.setItem(new Torch(gameMap.getCell(20, 4)));
        player.pickupItem();
        if (player.hasCellarKey()) {
            cellardoor.setOpen();
        }
        player.move(0,1);
        assertEquals(20, player.getX());
        assertEquals(4, player.getY());
    }

    @Test
    public void checkIfFighting(){
        gameMap.getCell(20,5).setType(CellType.FLOOR);
        gameMap.getCell(20,5).setActor(new Ghost(gameMap.getCell(20,5)));
        player = new Player(gameMap.getCell(20,4));
        player.attackIfEncounter(0, 1);
        assertTrue(player.isFighting());

    }
    @Test
    public void checkIfNotFighting(){
        gameMap.getCell(20,5).setType(CellType.FLOOR);
        gameMap.getCell(20,5).setActor(new Ghost(gameMap.getCell(20,5)));
        player = new Player(gameMap.getCell(20,4));
        player.attackIfEncounter(-8, 5);
        assertFalse(player.isFighting());

    }

    //todo if character hits walls, does it move
    //todo if character truly itself
}
