package com.codecool.dungeoncrawl.logic;

public class MapSaver {
    public static String saveMap(GameMap map) {
        StringBuilder sb = new StringBuilder();
        int width = map.getWidth();
        int height = map.getHeight();
        sb.append(width).append(" ").append(height).append("\n");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String currentCell = map.getCell(x, y).getIcon();
                    switch (currentCell) {
                        case "empty":
                            sb.append(" ");
                            break;
                        case "wall":
                            sb.append("#");
                            break;
                        case "floor":
                            sb.append(".");
                            break;
                        case "player":
                            sb.append("@");
                            break;
                        case "Cat":
                            sb.append("C");
                            break;
                        case "garden key":
                            sb.append("k");
                            break;
                        case "cellar key":
                            sb.append("K");
                            break;
                        case "cheese":
                            sb.append("c");
                            break;
                        case "Ghost of your mother-in-law":
                            sb.append("G");
                            break;
                        case "Wife":
                            sb.append("W");
                            break;
                        case "grass":
                            sb.append("+");
                            break;
                        case "cutted grass":
                            sb.append("-");
                            break;
                        case "apple":
                            sb.append("a");
                            break;
                        case "meat":
                            sb.append("h");
                            break;
                        case "spiderweb":
                            sb.append("w");
                            break;
                        case "bones":
                            sb.append("x");
                            break;
                        case "torch":
                            sb.append("t");
                            break;
                        case "lamp":
                            sb.append("l");
                            break;
                        case "light bulb":
                            sb.append("b");
                            break;
                        case "closed garden door":
                            sb.append("O");
                            break;
                        case "open garden door":
                            sb.append("O");
                            break;
                        case "closed cellar door":
                            sb.append("o");
                            break;
                        case "open cellar door":
                            sb.append("o");
                            break;
                        case "fence":
                            sb.append("f");
                            break;
                        case "gate":
                            sb.append("F");
                            break;
                        case "toilet":
                            sb.append("đ");
                            break;
                        case "tub":
                            sb.append("×");
                            break;
                        case "bed":
                            sb.append("Đ");
                            break;
                        case "tv":
                            sb.append("T");
                            break;
                        case "sofa1":
                            sb.append("1");
                            break;
                        case "sofa2":
                            sb.append("2");
                            break;
                        case "window":
                            sb.append("ß");
                            break;
                        case "stairUp":
                            sb.append("<");
                            break;
                        case "stairDown":
                            sb.append(">");
                            break;
                        case "cross1":
                            sb.append("3");
                            break;
                        case "cross2":
                            sb.append("4");
                            break;
                        case "books":
                            sb.append("B");
                            break;
                        case "mirror":
                            sb.append("m");
                            break;
                        case "bread":
                            sb.append("$");
                            break;
                        case "pear":
                            sb.append("p");
                            break;
                        case "carrot":
                            sb.append("r");
                            break;
                        case "ring":
                            sb.append("&");
                            break;
                        case "Scrub":
                            sb.append("S");
                            break;
                        case "ufo":
                            sb.append("7");
                            break;
                        case "beam":
                            sb.append("8");
                            break;
                        case "cow":
                            sb.append("9");
                            break;
                        case "knife":
                           sb.append("€");
                            break;
                        default:
                            sb.append("Ö");
                            break;
                    }
                }
            if (y != height - 1) {
                sb.append("\n");
            }
            }
        return sb.toString();
    }
}
