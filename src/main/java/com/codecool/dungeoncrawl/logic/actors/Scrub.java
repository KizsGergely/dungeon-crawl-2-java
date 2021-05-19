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

	public void moveScrub(int playerX, int playerY) {
		int scrubX = this.getX();
		int scrubY = this.getY();
		int distanceX = scrubX - playerX;
		int distanceY = scrubY - playerY;

		if (Math.abs(distanceX) < Math.abs(distanceY) || distanceX == 0) {
			if (distanceY < 0) {
				direction = Direction.SOUTH;
			} else {
				direction = Direction.NORTH;
			}
		} else if (Math.abs(distanceX) > Math.abs(distanceY) || distanceY == 0
			|| distanceX == distanceY){
			if (distanceX < 0) {
				direction = Direction.EAST;
			} else {
				direction = Direction.WEST;
			}
		}
		move(direction.getDx(), direction.getDy());
	}

}
