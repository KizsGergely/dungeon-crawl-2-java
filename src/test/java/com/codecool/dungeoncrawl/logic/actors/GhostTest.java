package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GhostTest {
    static Cell cell = null;
    static GameMap gameMap = null;

    @BeforeAll
    public static void setUp (){
        gameMap = new GameMap(20,20, CellType.EMPTY);
        cell = new Cell(gameMap,10,10, CellType.EMPTY);
    }

    @Test
    public void isRandomMovementWork(){
        Ghost ghost = new Ghost(cell);
        int originalX = ghost.getX();
        int originalY = ghost.getY();
        ghost.moveGhostRandomly(gameMap.getWidth(),gameMap.getHeight());
        boolean ghostMoved = originalX != ghost.getX() || originalY != ghost.getY();

        assertTrue(ghostMoved);
    }

//    @Test
//    public void ifGhostIsReallyItself(){
//
//        Ghost ghost = new Ghost(cell);
//        cell.setActor(ghost);
//        System.out.println(ghost.getCell().getX());
//        System.out.println(ghost.getCell().getY());
    //        assertEquals("ghost", gameMap.getCell(10, 10).getActor().getTileName());
//    }


}
