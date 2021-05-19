package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Direction;

public class Scrub extends Actor{
	public Scrub(Cell cell) {
		super(cell);
	}

	@Override
	public String getTileName() {
		return "Scrub";
	}

	public void moveScrubAround(int width, int height) {
		direction = Direction.values()[random.nextInt(4)];
		moveActors(direction.getDx(), direction.getDy(), width, height);
	}

}
