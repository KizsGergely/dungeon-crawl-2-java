package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class StairUp extends Environment{
	public StairUp(Cell cell) {
		super(cell);
	}

	@Override
	public String getTileName() {
		return "stairUp";
	}
}
