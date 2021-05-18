package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Direction;

public class Ghost extends Actor{
	private Cell cell = getCell();
	public Ghost(Cell cell) {
		super(cell);
	}

	@Override
	public String getTileName() {
		return "ghost";
	}

	private void moveGhost(int dx, int dy) {
		Cell nextCell = cell.getNeighbor(dx, dy);
		if (nextCell.getActor() == null) {
			cell.setActor(null);
			nextCell.setActor(this);
			cell = nextCell;
		}
	}

	@Override
	public void moveRandomly() {
		direction = Direction.values()[random.nextInt(4)];
		moveGhost(direction.getDx(), direction.getDy());
	}

}
