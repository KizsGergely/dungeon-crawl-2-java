package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Direction;

import java.util.Random;

public class Cat extends Actor {

    public Cat(Cell cell) {
        super(cell);
        health = 9;
        attack = 3;
        defense = 100;
    }

    @Override
    public String getTileName() {
        return "Cat";
    }

}
