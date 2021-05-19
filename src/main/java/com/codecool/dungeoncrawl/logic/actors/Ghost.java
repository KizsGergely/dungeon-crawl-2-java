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
		return "Ghost";
	}

	private void moveGhost(int dx, int dy, int width, int height) {
		int currentX = this.getX();
		int currentY = this.getY();
		if (currentX + dx < width &&
			currentX + dx >= 0 &&
			currentY + dy < height &&
			currentY + dy >= 0) {
			Cell nextCell = cell.getNeighbor(dx, dy);
			if (nextCell.getActor() == null) {
				cell.setActor(null);
				nextCell.setActor(this);
				cell = nextCell;
			}
		}
	}

	public void moveGhostRandomly(int width, int height) {
		direction = Direction.values()[random.nextInt(4)];
		moveGhost(direction.getDx(), direction.getDy(), width, height);
	}

}
