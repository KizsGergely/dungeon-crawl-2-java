package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Wife extends Actor{
	public Wife(Cell cell) {
		super(cell);
		attack = 100;
		defense = 100;
	}

	@Override
	public String getTileName() {
		return "Wife";
	}
}
