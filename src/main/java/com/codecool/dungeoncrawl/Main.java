package com.codecool.dungeoncrawl;


import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.model.GameState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.environment.CellarDoor;
import com.codecool.dungeoncrawl.logic.environment.GardenDoor;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.sql.Date;
import java.util.*;

public class Main extends Application {
    int level = 1;
    GameMap map1 = MapLoader.loadMap(1);
    GameMap map2 = MapLoader.loadMap(2);
    Player player = new Player(map1.getCell(20, 4));
    GameMap map = map1;
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    GridPane ui = new GridPane();
    GridPane textUi = new GridPane();
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
    GameDatabaseManager dbManager;
    ModalHandler modal = new ModalHandler();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setupDbManager();
        Label welcome = new Label("Welcome!\n\nPlease enter your name:");
        TextField nameInput = new TextField("What is your name? ");
        Button confirm = new Button("OK");
        VBox layout = new VBox(2);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(welcome, nameInput, confirm);
        Scene nameScene = new Scene(layout, 300, 150);
        Stage nameStage = new Stage();
        nameStage.setTitle("Name yourself!");
        nameStage.setScene(nameScene);
        nameStage.show();
        confirm.setOnAction(event -> {
            player.setName(nameInput.getText());
            nameStage.close();
            primaryStage.setTitle("lwarC noegnuD");
            primaryStage.show();
            refresh();
        });
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

        MenuBar menuBar = new MenuBar();
        Menu menuGame = new Menu("Game");
        MenuItem menuExport = new MenuItem("Export game");
        menuExport.setOnAction(event -> {});
        MenuItem menuImport = new MenuItem("Import game");
        menuImport.setOnAction(event -> {});
        MenuItem menuLoad = new MenuItem("Load game state");
        menuLoad.setOnAction(event -> {
            System.out.println("asda");
        });
        menuGame.getItems().addAll(menuExport, menuImport, menuLoad);
        menuBar.getMenus().addAll(menuGame);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);
        borderPane.setTop(menuBar);
        borderPane.setBottom(textUi);

        Scene scene = new Scene(borderPane);
//        textUi.getChildren().addAll(menuBar);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        groupMonsters();

        player.setGrassToCut(map.getGrassCounter());
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
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
            case S:
                if (keyEvent.isControlDown()) {
                    String currentMap = getCurrentMapAsString();
                    String otherMap = getOtherMapAsString();
                    modal.saveGameModal(dbManager, currentMap, otherMap, player);
                }
                break;
            case H:
//                Gson gson = new Gson();
//                String json = gson.toJson(this.player);
//                System.out.println(json);
//                try (FileWriter writer = new FileWriter("staff.json")) {
//                    gson.toJson(this.player, writer);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                ObjectMapper mapper = new ObjectMapper();
//                mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
//                String jsonResult = null;
//                try {
//                    jsonResult = mapper.writeValueAsString(this.player);
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(jsonResult);



//                System.out.println(this.player);
//                try{
//                    //Creating the object
//                    //Creating stream and writing the object
//                    FileOutputStream fout=new FileOutputStream("f.txt");
//                    ObjectOutputStream out=new ObjectOutputStream(fout);
//                    out.writeObject(player);
//                    out.flush();
//                    //closing the stream
//                    out.close();
//                    System.out.println("success");
//                }catch(Exception e){System.out.println("create object " + e);}
//
//                try{
//                    //Creating stream to read the object
//                    ObjectInputStream in=new ObjectInputStream(new FileInputStream("f.txt"));
//                    Player s=(Player)in.readObject();
//                    //printing the data of the serialized object
//                    System.out.println(s.getName());
//                    this.player = s;
//                    map.setPlayer(this.player);
//                    System.out.println("set player");
//                    map.setCell(this.player, this.player.getX(), this.player.getY());
//                    System.out.println("set map cell");
//                    this.player.setCell(map.getCell(this.player.getX(), this.player.getY()));
//                    System.out.println("set player cell");
//
//                    //closing the stream
//                    in.close();
//                }catch(Exception e){System.out.println("create stream " + e);}
//                System.out.println(this.player);

            case L:
                System.out.println(MapSaver.saveMap(map));
                break;
            case I:
                List<GameState> load = dbManager.loadAllGame();
                System.out.println(load);
                System.out.println(load.size());
                break;
            case M:
                modal.loadGameModal(dbManager);
                break;
        }


        player.attackIfEncounter(dx, dy);
        removeMonstersIfDead(ghosts, scrubs);
        checkIfDead();
        player.move(dx, dy);
        if (level != player.getLevelNumber()) changeMap();
        if (player.isWifeHappy()) win();
        refresh();
    }



    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }

    private void checkIfDead() {
        if (player.checkIfDead()) {
            Label endText = new Label("Oops! You're dead!");
            Button endButton = new Button("I know! :(");
            VBox endBox = new VBox(2);
            endBox.setPadding(new Insets(10, 10, 10, 10));
            endBox.getChildren().addAll(endText, endButton);
            Scene endScene = new Scene(endBox, 200, 100);
            Stage endStage = new Stage();
            endStage.setTitle("Haha!");
            endStage.setScene(endScene);
            endStage.show();
            endButton.setOnAction(event -> {
                endStage.close();
                System.exit(0);
            });
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
                        case "Ghost of your mother-in-law":
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
            scrub.moveScrub(player.getX(), player.getY(), player.getHasTorch());
        }
    }

    private void refresh() {
//        context.setFill(Color.BLACK);
        context.setFill(Color.color(0.278431373F,0.176470588F,0.235294118F));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int minX = Math.max(player.getX() - viewHorizontal, 0);
        int maxX = Math.min(map.getWidth(),player.getX() + viewHorizontal);
        int minY = Math.max(player.getY() - viewVertical, 0);
        int maxY = Math.min(map.getHeight(),player.getY() + viewVertical);

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
        playerNameCheck();
    }

    private void pickup() {
        setPickupVisibility(true);
        yesPickupButton.setOnAction(event -> {
            if (player.isWifeHappy() && player.getCell().getItem() == null)
                System.exit(0);
            player.pickupItem();
            if (player.hasCellarKey()) openCellarDoor();
            if (player.hasGardenKey()) openGardenDoor();
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

    private void openCellarDoor() {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x <map.getWidth(); x++) {
                if (map.getCell(x,y).getEnvironment() instanceof CellarDoor) {
                    ((CellarDoor) map.getCell(x,y).getEnvironment()).setOpen();
                }
            }
        }
    }

    private void openGardenDoor() {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x <map.getWidth(); x++) {
                if (map.getCell(x,y).getEnvironment() instanceof GardenDoor) {
                    ((GardenDoor) map.getCell(x,y).getEnvironment()).setOpen();
                }
            }
        }
    }

    private void changeMap() {
        GameMap currentMap = map;
        map.setPlayer(null);
        map.setCell(null, player.getX(), player.getY());
        level = player.getLevelNumber();
        switch (level) {
            case 1:
                map2 = currentMap;
                map = map1;
                break;
            case 2:
                map1 = currentMap;
                map = map2;
                break;
        }
        if (level == 1) {
            map.setCell(player,2, 2);
            player.setCell(map.getCell(2,2));
        } else {
            map.setCell(player,2, 10);
            player.setCell(map.getCell(2,10));
        }
        map.setPlayer(player);
        groupMonsters();
        moveMonsters();
        refresh();
    }

    private void playerNameCheck() {
        ArrayList<String> developers = new ArrayList<>();
        developers.add("allisC");
        developers.add("ireG");
        developers.add("iloZ");
        boolean isDev = developers.contains(player.getName());
        if (isDev) player.makeWifeHappy();
    }

    private void win() {
        textLabel.setText("Your wife is happy, you won! That's what matters the most! Wink wink.");
        setPickupVisibility(true);
        pickupLabel.setText("Your wife is happy!\nYou won life!");
//        Gson gson = new Gson();
//        String json = gson.toJson(player);
//        System.out.println(json);
    }

    private String getCurrentMapAsString() {
        return MapSaver.saveMap(map);
    }

    private String getOtherMapAsString() {
        return (level == 1) ? MapSaver.saveMap(map2) : MapSaver.saveMap(map1);
    }

}