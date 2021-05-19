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
    GridPane textUi = new GridPane();
    Player player = map.getPlayer();
    Inventory inventory = player.getInventory();
    ArrayList<Cat> cats = new ArrayList<>();
    ArrayList<Ghost> ghosts = new ArrayList<>();
    ArrayList<Scrub> scrubs = new ArrayList<>();
    ArrayList<Wife> wives = new ArrayList<>();
    Label playerNameLabel = new Label();
    Label playerHealthLabel = new Label();
    Label playerAttackLabel = new Label();
    Label playerDefenseLabel = new Label();
    Label monsterNameLabel = new Label();
    Label monsterHealthLabel = new Label();
    Label monsterAttackLabel = new Label();
    Label monsterDefenseLabel = new Label();
    Label textLabel = new Label();
    Label inventoryLabel = new Label(inventory.toString());
    Label pickupLabel = new Label("Pick up?");
    Button yesPickupButton = new Button("Yes");
    Button noPickupButton = new Button("No");
    int viewHorizontal = 17;
    int viewVertical = 13;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ui.setPrefWidth(200);
        textUi.setPrefWidth(50);
        ui.setPadding(new Insets(10));
        textUi.setPadding(new Insets(10));

        ui.add(pickupLabel, 0, 0);
        ui.add(yesPickupButton, 0,1);
        ui.add(noPickupButton, 1, 1);
        setPickupVisibility(false);
        ui.add(new Label("Inventory: "), 0, 2);
        ui.add(inventoryLabel, 0, 3);

        textUi.add(playerNameLabel, 0, 0);
        textUi.add(playerHealthLabel, 1, 0);
        textUi.add(playerAttackLabel, 2, 0);
        textUi.add(playerDefenseLabel, 3, 0);
        textUi.add(monsterNameLabel, 0, 1);
        textUi.add(monsterHealthLabel, 1, 1);
        textUi.add(monsterAttackLabel, 2, 1);
        textUi.add(monsterDefenseLabel, 3, 1);
        textUi.add(new Label(" "), 0, 2);
        textUi.add(textLabel, 4, 3);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);
        borderPane.setTop(textUi);

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
        removeMonstersIfDead(ghosts, scrubs);
        restartGameIfDead();
        player.move(dx, dy);
        refresh();

    }

    private void restartGameIfDead() {
        // now it just exits
        if (player.checkIfDead()) {
            System.exit(0);
        }
    }

    private void removeMonstersIfDead(ArrayList<Ghost> ghosts, ArrayList<Scrub> scrubs) {
        int ghostIndex = -1;
        int scrubIndex = -1;
        // remove monster from map
        for (int i = 0; i < ghosts.size(); i++) {
            if (ghosts.get(i).checkIfDead()) {
                ghosts.get(i).getCell().setActor(null);
                ghostIndex = i;
                break;
            }
        }
        for (int i = 0; i < scrubs.size(); i++) {
            if (scrubs.get(i).checkIfDead()) {
                scrubs.get(i).getCell().setActor(null);
                scrubIndex = i;
                break;
            }
        }
        // remove monster from the list where it is collected with the other similar monsters
        if (ghostIndex > -1) {
            ghosts.remove(ghostIndex);
        }
        if (scrubIndex > -1) {
            scrubs.remove(scrubIndex);
        }
    }

    private void groupMonsters() {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x <map.getWidth(); x++) {
                if (map.getCell(x,y).getActor() != null) {
                    switch (map.getCell(x, y).getActor().getTileName()) {
                        case "Cat":
                            cats.add((Cat) map.getCell(x, y).getActor());
                            break;
                        case "Ghost":
                            ghosts.add((Ghost) map.getCell(x, y).getActor());
                            break;
                        case "Wife":
                            wives.add((Wife) map.getCell(x, y).getActor());
                            break;
                        case "Scrub":
                            scrubs.add((Scrub) map.getCell(x, y).getActor());
                            break;
                    }
                }
            }
        }
    }

    private void moveMonsters() {
        for (Cat skeleton: cats) {
            skeleton.moveRandomly();
        }
        for (Ghost ghost: ghosts) {
            ghost.moveGhostRandomly(map.getWidth(), map.getHeight());
        }
        for (Scrub scrub: scrubs) {
            scrub.moveScrubAround(map.getWidth(), map.getHeight());
        }
    }

    private void refresh() {
//        context.setFill(Color.BLACK);
        context.setFill(Color.color(0.278431373F,0.176470588F,0.235294118F));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int minX = Math.max(map.getPlayer().getX() - viewHorizontal, 0);
        int maxX = Math.min(map.getWidth(),map.getPlayer().getX() + viewHorizontal);
        int minY = Math.max(map.getPlayer().getY() - viewVertical, 0);
        int maxY = Math.min(map.getHeight(),map.getPlayer().getY() + viewVertical);

        if(minX == 0) maxX = Math.min(2 * viewHorizontal + 1, map.getWidth() -1);
        if(minY == 0) maxY = Math.min(2 * viewVertical + 1, map.getHeight() -1);
        if(maxX == map.getWidth() - 1) minX = Math.max(map.getWidth() - 2 - viewHorizontal * 2, 0);
        if(maxY == map.getHeight() - 1) minY = Math.max(map.getHeight() - 2 - viewVertical * 2, 0);

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x - minX, y-minY);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x - minX, y - minY);
                } else if (cell.getEnvironment() != null) {
                    Tiles.drawTile(context, cell.getEnvironment(), x - minX, y - minY);
                } else {
                    Tiles.drawTile(context, cell, x - minX, y - minY);
                }
            }
        }
        if (player.canPickup()) pickup();
        inventoryLabel.setText(player.getInventory().toString());
        getPlayerStats();
        if (player.isFighting()) getMonsterStats();
        else hideMonsterStats();
    }
    private void pickup() {
        setPickupVisibility(true);
        yesPickupButton.setOnAction(event -> {
            player.pickupItem();
            refresh();
            setPickupVisibility(false);
        });
        noPickupButton.setOnAction(event -> setPickupVisibility(false));
    }

    private void setPickupVisibility(boolean state) {
        pickupLabel.setVisible(state);
        yesPickupButton.setVisible(state);
        noPickupButton.setVisible(state);
    }

    private void getPlayerStats() {
        playerNameLabel.setText("" + player.getName());
        playerHealthLabel.setText(" | Health: " + player.getHealth());
        playerAttackLabel.setText(" | Attack: " + player.getAttack());
        playerDefenseLabel.setText(" | Defense: " + player.getDefense());
    }

    private void getMonsterStats() {
        if (player.getOpponent() != null) {
            monsterNameLabel.setText("" + player.getOpponent().getTileName());
            monsterHealthLabel.setText(" | Health: " + player.getOpponent().getHealth());
            monsterAttackLabel.setText(" | Attack: " + player.getOpponent().getAttack());
            monsterDefenseLabel.setText(" | Defense: " + player.getOpponent().getDefense());
            if (player.isKilledAMonster()) {
                textLabel.setText("You killed the " + player.getKilledMonsterName() + "!");
            }
        }
    }

    private void hideMonsterStats() {
        monsterNameLabel.setText("");
        monsterHealthLabel.setText("");
        monsterAttackLabel.setText("");
        monsterDefenseLabel.setText("");
        textLabel.setText("");
    }
}