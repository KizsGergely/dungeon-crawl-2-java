package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Direction;

public class Ghost extends Actor{
	public Ghost(Cell cell) {
		super(cell);
	}

	@Override
	public String getTileName() {
		return "Ghost of your mother-in-law";
	}


	public void moveGhostRandomly(int width, int height) {
		direction = Direction.values()[random.nextInt(4)];
		moveActors(direction.getDx(), direction.getDy(), width, height);
	}

}
