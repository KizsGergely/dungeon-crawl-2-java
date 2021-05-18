package com.codecool.dungeoncrawl.logic;

public enum Direction {
	NORTH(-1,0),
	WEST(0,-1),
	SOUTH(1,0),
	EAST(0,1);

	int dx;
	int dy;

	Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}
}
