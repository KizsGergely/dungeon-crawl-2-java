package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Wife extends Actor{
	public Wife(Cell cell) {
		super(cell);
	}

	@Override
	public String getTileName() {
		return "Wife";
	}
}
