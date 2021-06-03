package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;
    private String GameName = "GameName";
    private PlayerModel playerModel;
    private DataSource dataSource;
    private GameState gameState;

    public void setup() throws SQLException {
        dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource, playerDao);
    }

    public void savePlayer(Player player) {
        playerModel = new PlayerModel(player);
        playerDao.add(playerModel);
    }

    public void savePlayerToJson(PlayerModel playerModel) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        String jsonResult = null;
        try {
            jsonResult = mapper.writeValueAsString(playerModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonResult);
        try {
            mapper.writeValue(Paths.get(playerModel.getPlayerName() + ".json").toFile(), playerModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGameStateToJson(GameState gameState) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        String jsonResult = null;
        try {
            jsonResult = mapper.writeValueAsString(gameState);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonResult);
        try {
            mapper.writeValue(Paths.get(gameState.getSaveName() + ".json").toFile(), gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGame(String currentMap, String otherMap, Date savedAt, String saveName, Player player) {
        savePlayer(player);
        GameState gameState = new GameState(currentMap, otherMap, savedAt, playerModel, saveName);
        gameStateDao.add(gameState);
    }

    public void updateGame(String currentMap, String otherMap, Date savedAt, String saveName, Player player) {
        savePlayer(player);
        GameState gameState = new GameState(currentMap, otherMap, savedAt, playerModel, saveName);
        gameStateDao.update(gameState, saveName);
    }

    public GameState loadGame() {
        return gameStateDao.get(1);
    }

    public List<GameState> loadAllGame() {
        return gameStateDao.getAll();
    }

    public List<GameState> loadAllGameIdAndName() {
        return gameStateDao.getAll();
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    public boolean checkIfSaveNameExists(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT count(1) > 0 FROM game_state WHERE save_name = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
