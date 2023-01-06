package com.rest.apidemo.dao;

import com.rest.apidemo.model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO {

    private final Connection conn;

    public PlayerDAOImpl() {
        MySQLDbConnection db = new MySQLDbConnection();
        this.conn = db.getDbConnection();
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> players = new LinkedList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("select * from players");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                players.add(new Player(set.getString("name"),
                        set.getInt("age"),
                        set.getString("nationality"),
                        set.getString("club"),
                        set.getString("position")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return players;
    }

    @Override
    public List<Player> getPlayers(String club) {
        List<Player> players = new LinkedList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("select * from players where club = ?");
            statement.setString(1, club);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                players.add(new Player(set.getString("name"),
                        set.getInt("age"),
                        set.getString("nationality"),
                        set.getString("club"),
                        set.getString("position")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return players;
    }

    @Override
    public Player getPlayer(String name) {
        Player player = null;
        try {
            PreparedStatement statement = conn.prepareStatement("select * from players where name=?");
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                player = new Player(set.getString("name"),
                        set.getInt("age"),
                        set.getString("nationality"),
                        set.getString("club"),
                        set.getString("position"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return player;
    }
}
