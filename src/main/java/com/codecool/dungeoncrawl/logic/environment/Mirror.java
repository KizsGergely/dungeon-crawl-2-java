package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Mirror extends Environment{
		public Mirror(Cell cell) {
			super(cell);
		}

		@Override
		public String getTileName() {
			return "mirror";
		}

}
