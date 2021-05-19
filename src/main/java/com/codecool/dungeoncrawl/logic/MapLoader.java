package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.logic.environment.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map2.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'C':
                            cell.setType(CellType.FLOOR);
                            new Cat(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            new Cheese(cell);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'G':
                            cell.setType(CellType.FLOOR);
                            new Ghost(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new GardenKey(cell);
                            break;
                        case 'K':
                            cell.setType(CellType.FLOOR);
                            new CellarKey(cell);
                            break;
                        case 'W':
                            cell.setType(CellType.FLOOR);
                            new Wife(cell);
                            break;
                        case '+':
                            cell.setType(CellType.FLOOR);
                            new Grass(cell);
                            break;
                        case '-':
                            cell.setType(CellType.FLOOR);
                            new CuttedGrass(cell);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Apple(cell);
                            break;
                        case 'h':
                            cell.setType(CellType.FLOOR);
                            new Meat(cell);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new SpiderWeb(cell);
                            break;
                        case 'x':
                            cell.setType(CellType.FLOOR);
                            new Bones(cell);
                            break;
                        case 't':
                            cell.setType(CellType.FLOOR);
                            new Torch(cell);
                            break;
                        case 'l':
                            cell.setType(CellType.FLOOR);
                            new Lamp(cell);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            new LightBulb(cell);
                            break;
                        case 'o':
                            cell.setType(CellType.FLOOR);
                            new ClosedDoor(cell);
                            break;
                        case 'O':
                            cell.setType(CellType.FLOOR);
                            new OpenDoor(cell);
                            break;
                        case 'f':
                            cell.setType(CellType.FLOOR);
                            new Fence(cell);
                            break;
                        case 'F':
                            cell.setType(CellType.FLOOR);
                            new Gate(cell);
                            break;
                        case 'đ':
                            cell.setType(CellType.FLOOR);
                            new Toilet(cell);
                            break;
                        case '×':
                            cell.setType(CellType.FLOOR);
                            new Tub(cell);
                            break;
                        case 'Đ':
                            cell.setType(CellType.FLOOR);
                            new Bed(cell);
                            break;
                        case 'T':
                            cell.setType(CellType.FLOOR);
                            new Tv(cell);
                            break;
                        case '1':
                            cell.setType(CellType.FLOOR);
                            new Sofa1(cell);
                            break;
                        case '2':
                            cell.setType(CellType.FLOOR);
                            new Sofa2(cell);
                            break;
                        case 'ß':
                            cell.setType(CellType.FLOOR);
                            new Window(cell);
                            break;
                        case '<':
                            cell.setType(CellType.FLOOR);
                            new StairUp(cell);
                            break;
                        case '>':
                            cell.setType(CellType.FLOOR);
                            new StairDown(cell);
                            break;
                        case '3':
                            cell.setType(CellType.FLOOR);
                            new Cross1(cell);
                            break;
                        case '4':
                            cell.setType(CellType.FLOOR);
                            new Cross2(cell);
                            break;
                        case 'B':
                            cell.setType(CellType.FLOOR);
                            new Book(cell);
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            new Mirror(cell);
                            break;
                        case '$':
                            cell.setType(CellType.FLOOR);
                            new Bread(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new Pear(cell);
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            new Carrot(cell);
                            break;
                        case '&':
                            cell.setType(CellType.FLOOR);
                            new Ring(cell);
                            break;
                        case 'S':
                            cell.setType(CellType.FLOOR);
                            new Scrub(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}
