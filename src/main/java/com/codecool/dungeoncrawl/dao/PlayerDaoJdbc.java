package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_name, hp, attack, defense, x, y, is_cat_fed, is_grass_cut, " +
                    "grass_to_cut, on_level, inventory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getAttack());
            statement.setInt(4, player.getDefense());
            statement.setInt(5, player.getX());
            statement.setInt(6, player.getY());
            statement.setBoolean(7, player.isCatFed());
            statement.setBoolean(8, player.isGrassCut());
            statement.setInt(9, player.getGrassToCut());
            statement.setInt(10, player.getOnLevel());
            statement.setString(11, player.getInventoryAsString());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE player SET player_name = ?, hp = ?, attack = ?, defense = ?, x = ?, y = ?," +
                    " is_cat_fed = ?, is_grass_cut = ?, grass_to_cut = ?, on_level = ?, inventory = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getAttack());
            statement.setInt(4, player.getDefense());
            statement.setInt(5, player.getX());
            statement.setInt(6, player.getY());
            statement.setBoolean(7, player.isCatFed());
            statement.setBoolean(8, player.isGrassCut());
            statement.setInt(9, player.getGrassToCut());
            statement.setInt(10, player.getOnLevel());
            statement.setString(11, player.getInventoryAsString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlayerModel get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM player WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;
            PlayerModel playerModel = new PlayerModel(resultSet.getString(2), resultSet.getInt(3),
                    resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
                    resultSet.getInt(7), resultSet.getBoolean(8), resultSet.getBoolean(9),
                    resultSet.getInt(10), resultSet.getInt(11), resultSet.getString(12));
            playerModel.setId(resultSet.getInt(1));
            return playerModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PlayerModel> getAll() {
        return null;
    }



}
