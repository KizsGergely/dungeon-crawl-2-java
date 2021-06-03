package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.dao.PlayerDaoJdbc;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.List;

public class ModalHandler {

    public void saveGameModal(GameDatabaseManager dbManager, String currentMap, String otherMap, Player player) {
        TextField nameInput = new TextField("Name");
        Button save = new Button("Save");
        Button cancel = new Button("Cancel");
        VBox layout = new VBox(2);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(nameInput, save, cancel);
        Scene saveScene = new Scene(layout, 350, 150);
        Stage saveStage = new Stage();
        saveStage.setTitle("Save game state");
        saveStage.setScene(saveScene);
        saveStage.show();
        save.setOnAction(event -> {
            // check if save_name exists
            checkSaveName(dbManager, currentMap, otherMap, player, nameInput, saveStage);
        });
        cancel.setOnAction(event -> saveStage.close());
    }

    private void checkSaveName(GameDatabaseManager dbManager, String currentMap, String otherMap, Player player, TextField nameInput, Stage saveStage) {
        nameInput.requestFocus();
        String saveName = nameInput.getText();
        if (dbManager.checkIfSaveNameExists(saveName)) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Save name already exists");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Would you like to overwrite the already existing state?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            confirmAlert.getButtonTypes().setAll(yesButton, noButton);
            confirmAlert.showAndWait().ifPresent(type -> {
                if (type.getButtonData() == ButtonBar.ButtonData.YES) {
                    dbManager.updateGame(currentMap, otherMap, new Date(System.currentTimeMillis()), saveName, player);
                    saveStage.close();
                } else {
                    confirmAlert.close();  //TODO
                    checkSaveName(dbManager, currentMap, otherMap, player, nameInput, saveStage);
                    }
            });
        } else {
            dbManager.saveGame(currentMap, otherMap, new Date(System.currentTimeMillis()), saveName, player);
        }
    }

    public void loadGameModal(GameDatabaseManager dbManager) {
        List<GameState> gameStates = dbManager.loadAllGameIdAndName();
        TableView tableView = new TableView();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<GameState, String> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("gameId"));  //field

        TableColumn<GameState, String> column2 = new TableColumn<>("Save name");
        column2.setCellValueFactory(new PropertyValueFactory<>("saveName"));

        tableView.setPlaceholder(new Label("No saves to display"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        for (GameState gs : gameStates) {
            tableView.getItems().add(gs);
        }

        TableView.TableViewSelectionModel selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        ObservableList<Integer> selectedIndices = selectionModel.getSelectedIndices();
        System.out.println(selectedIndices);
        VBox layout = new VBox(tableView);
        layout.setPadding(new Insets(10, 10, 10, 10));
        Scene loadScene = new Scene(layout, 500, 300);
        Stage loadStage = new Stage();
        loadStage.setTitle("Load game state");
        loadStage.setScene(loadScene);
        loadStage.show();
    }

}
