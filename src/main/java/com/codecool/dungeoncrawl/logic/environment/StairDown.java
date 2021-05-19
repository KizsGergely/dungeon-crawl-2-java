package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class StairDown extends Environment{
		public StairDown(Cell cell) {
			super(cell);
		}

		@Override
		public String getTileName() {
			return "stairDown";
		}
}

