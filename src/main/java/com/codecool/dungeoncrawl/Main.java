package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    GridPane ui = new GridPane();
    Label healthLabel = new Label();
    ArrayList<Skeleton> skeletons = new ArrayList<>();
    ArrayList<Ghost> ghosts = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();
    Player player = map.getPlayer();
    Inventory inventory = player.getInventory();
    Label inventoryLabel = new Label(inventory.toString());
    Label pickupLabel = new Label("Pick up?");
    Button yesPickupButton = new Button("Yes");
    Button noPickupButton = new Button("No");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("------------"), 0, 1);
        ui.add(pickupLabel, 0, 3);
        ui.add(yesPickupButton, 0,4);
        ui.add(noPickupButton, 1, 4);
        setPickupVisibility(false);
        ui.add(new Label("Inventory: "), 0, 5);
        ui.add(inventoryLabel, 1, 6);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        groupMonsters();

    }

    private void onKeyPressed(KeyEvent keyEvent) {
        int dx = 0;
        int dy = 0;
        moveMonsters();
        switch (keyEvent.getCode()) {
            case UP:
                dx = 0;
                dy = -1;
                break;
            case DOWN:
                dx = 0;
                dy = 1;
                break;
            case LEFT:
                dx = -1;
                dy = 0;
                break;
            case RIGHT:
                dx = 1;
                dy = 0;
                break;
        }
        player.attackIfEncounter(dx, dy);
        removeMonstersIfDead(skeletons, ghosts, enemies);
        player.move(dx, dy);
        refresh();

    }

    private void removeMonstersIfDead(ArrayList<Skeleton> skeletons, ArrayList<Ghost> ghosts, ArrayList<Enemy> enemies) {
        for (Skeleton skeleton: skeletons) {
            if (skeleton.checkIfDead()) {
                skeleton.getCell().setActor(null);
            }
        }
        for (Ghost ghost: ghosts) {
            if (ghost.checkIfDead()) {
                ghost.getCell().setActor(null);
            }
        }
        for (Enemy enemy: enemies) {
            if (enemy.checkIfDead()) {
                enemy.getCell().setActor(null);
            }
        }
    }

    private void groupMonsters() {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x <map.getWidth(); x++) {
                if (map.getCell(x,y).getActor() != null) {
                    if (map.getCell(x,y).getActor().getTileName().equals("skeleton")) {
                        skeletons.add((Skeleton) map.getCell(x,y).getActor());
                    } else if (map.getCell(x,y).getActor().getTileName().equals("ghost")) {
                        ghosts.add((Ghost) map.getCell(x,y).getActor());
                    } else if (map.getCell(x,y).getActor().getTileName().equals("enemy")) {
                        enemies.add((Enemy) map.getCell(x,y).getActor());
                    }
                }
            }
        }
    }

    private void moveMonsters() {
        for (Skeleton skeleton: skeletons) {
            skeleton.moveRandomly();
        }
        for (Ghost ghost: ghosts) {
            ghost.moveGhostRandomly(map.getWidth(), map.getHeight());
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        if (player.canPickup()) pickup();
        inventoryLabel.setText(map.getPlayer().getInventory().toString());
        healthLabel.setText("" + map.getPlayer().getHealth());
    }

    public void pickup() {
        setPickupVisibility(true);
        yesPickupButton.setOnAction(event -> {
            player.pickupItem();
            refresh();
            setPickupVisibility(false);
        });
        noPickupButton.setOnAction(event -> {
            setPickupVisibility(false);
        });
    }

    private void setPickupVisibility(boolean state) {
        pickupLabel.setVisible(state);
        yesPickupButton.setVisible(state);
        noPickupButton.setVisible(state);
    }
}
