package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Direction;

public class Scrub extends Actor{
	private int lightDistance = 3;
	public Scrub(Cell cell) {
		super(cell);
	}

	@Override
	public String getTileName() {
		return "Scrub";
	}

	public void moveScrub(int playerX, int playerY, boolean hasTorch) {
		int scrubX = this.getX();
		int scrubY = this.getY();
		int distanceX = scrubX - playerX;
		int distanceY = scrubY - playerY;
		if (hasTorch) {
			if (Math.abs(distanceY) > Math.abs(distanceX)) {
				if (distanceY >= -lightDistance || distanceY >= lightDistance) {
					direction = Direction.NORTH;
				} else {
					direction = Direction.SOUTH;
				}
			} else {
				if (distanceX >= -lightDistance || distanceX >= lightDistance) {
					direction = Direction.WEST;
				} else {
					direction = Direction.EAST;
				}
			}

		} else {
			if (Math.abs(distanceX) < Math.abs(distanceY)) {
				if (distanceY < 0) {
					direction = Direction.SOUTH;
				} else {
					direction = Direction.NORTH;
				}
			} else {
				if (distanceX < 0) {
					direction = Direction.EAST;
				} else {
					direction = Direction.WEST;
				}
			}
		}

		move(direction.getDx(), direction.getDy());
	}

}
