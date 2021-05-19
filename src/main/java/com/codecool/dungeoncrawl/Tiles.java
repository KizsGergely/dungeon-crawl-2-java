package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));  //
        tileMap.put("wall", new Tile(10, 17));  // #
        tileMap.put("floor", new Tile(17, 0));  // .
        tileMap.put("player", new Tile(25, 0));  // @
        tileMap.put("Cat", new Tile(30, 7));  // C
        tileMap.put("garden key", new Tile(16, 23));  // k
        tileMap.put("cellar key", new Tile(17, 23));  // K
        tileMap.put("cheese", new Tile(18, 28));  // c
        tileMap.put("Ghost", new Tile(26, 6));  // G
        tileMap.put("Wife", new Tile(24, 4));  // W
        tileMap.put("grass", new Tile(0, 2));  // +
        tileMap.put("cutted grass", new Tile(5, 0));  //-
        tileMap.put("apple", new Tile(15, 29));  // a
        tileMap.put("meat", new Tile(16, 28));  // h
        tileMap.put("spiderweb", new Tile(2, 15));  // w
        tileMap.put("bones", new Tile(0, 15)); // x
        tileMap.put("torch", new Tile(4, 15)); // t
        tileMap.put("lamp", new Tile(1, 7)); // l
        tileMap.put("light bulb", new Tile(29, 11)); // b
        tileMap.put("closed door", new Tile(3, 4)); // o
        tileMap.put("open door", new Tile(4, 4)); // O
        tileMap.put("fence", new Tile(1, 3)); // f
        tileMap.put("gate", new Tile(3, 3)); // F
        tileMap.put("toilet", new Tile(12, 10)); //đ
        tileMap.put("tub", new Tile(13, 10)); // ×
        tileMap.put("bed", new Tile(5, 8)); // Đ
        tileMap.put("tv", new Tile(2, 8)); // T
        tileMap.put("sofa1", new Tile(6, 8)); // 1
        tileMap.put("sofa2", new Tile(7, 8)); // 2
        tileMap.put("window", new Tile(12, 17)); // ß
        tileMap.put("grass", new Tile(0, 2));  // +
        tileMap.put("cutted grass", new Tile(5, 0));  //-
        tileMap.put("apple", new Tile(15, 29));  // a
        tileMap.put("meat", new Tile(16, 28));  // h
        tileMap.put("spiderweb", new Tile(2, 15));  // w
        tileMap.put("bones", new Tile(0, 15)); // x
        tileMap.put("torch", new Tile(4, 15)); // t
        tileMap.put("lamp", new Tile(1, 7)); // l
        tileMap.put("light bulb", new Tile(29, 11)); // b
        tileMap.put("closed door", new Tile(3, 4)); // o
        tileMap.put("open door", new Tile(4, 4)); // O
        tileMap.put("fence", new Tile(1, 3)); // f
        tileMap.put("gate", new Tile(3, 3)); // F
        tileMap.put("toilet", new Tile(12, 10)); //đ
        tileMap.put("tub", new Tile(13, 10)); // ×
        tileMap.put("bed", new Tile(5, 8)); // Đ
        tileMap.put("tv", new Tile(2, 8)); // T
        tileMap.put("sofa1", new Tile(6, 8)); // 1
        tileMap.put("sofa2", new Tile(7, 8)); // 2
        tileMap.put("window", new Tile(12, 17)); // ß
        tileMap.put("stairUp", new Tile(2, 6)); // <
        tileMap.put("stairDown", new Tile(3, 6));// >
        tileMap.put("cross1", new Tile(25, 12)); // 3
        tileMap.put("cross2", new Tile(25, 13)); // 4
        tileMap.put("books", new Tile(3, 7)); // B
        tileMap.put("mirror", new Tile(0, 8)); // m
        tileMap.put("bread", new Tile(15, 28)); // $
        tileMap.put("pear", new Tile(16, 29)); // p
        tileMap.put("carrot", new Tile(18, 30)); // r
        tileMap.put("ring", new Tile(14, 28)); // &
        tileMap.put("Scrub", new Tile(24, 1)); // S

    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
