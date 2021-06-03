package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }
//    public int getPlayerId() {
//        try (Connection conn = dataSource.getConnection()) {
//            String sql = "INSERT INTO game_state (current_map, other_map, saved_at, player_id, save_name) VALUES (?, ?, ?, ?, ?)";
//            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, state.getCurrentMap());
//            statement.setString(2, state.getOtherMap());
//            statement.setDate(3, state.getSavedAt());
//            statement.setInt(4, state.g);
//            statement.setString(5, state.);
//            statement.executeUpdate();
//            ResultSet resultSet = statement.getGeneratedKeys();
//            resultSet.next();
//            state.setId(resultSet.getInt(1));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, other_map, saved_at, player_id, save_name) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setString(2, state.getOtherMap());
            statement.setDate(3, state.getSavedAt());
            statement.setInt(4, state.getPlayer().getId());
            statement.setString(5, state.getSaveName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState state, String saveName) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE game_state SET current_map = ?, other_map = ?, saved_at = ?, " +
                    "player_id = ?, save_name = ? WHERE save_name = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setString(2, state.getOtherMap());
            statement.setDate(3, state.getSavedAt());
            statement.setInt(4, state.getPlayer().getId());
            statement.setString(5, state.getSaveName());
            statement.setString(6, saveName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState get(int id) {
        return null;
    }

    @Override
    public List<GameState> getAll() {
        return null;
    }
}
