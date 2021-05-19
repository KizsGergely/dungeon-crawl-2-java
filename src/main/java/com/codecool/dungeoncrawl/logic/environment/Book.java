package com.codecool.dungeoncrawl.logic.environment;

import com.codecool.dungeoncrawl.logic.Cell;

public class Book extends Environment{
		public Book(Cell cell) {
			super(cell);
		}

		@Override
		public String getTileName() {
			return "books";
		}

}
