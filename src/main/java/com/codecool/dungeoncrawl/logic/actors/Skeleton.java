package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Direction;

import java.util.Random;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public void moveSkeleton() {
        Random random = new Random();
        direction = Direction.values()[random.nextInt(4)];
        move(direction.getDx(), direction.getDy());
    }
}
