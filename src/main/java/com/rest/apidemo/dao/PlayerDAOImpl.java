package com.rest.apidemo.dao;

import com.rest.apidemo.dao.exceptions.RTSQLIntegrityConstraintViolationException;
import com.rest.apidemo.dao.exceptions.RuntimeSqlException;
import com.rest.apidemo.model.Player;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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
            PreparedStatement statement = sql("select * from players");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                players.add(getPlayerInfoFromDb(res));
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
            PreparedStatement statement = sql("select * from players where club = ?");
            statement.setString(1, club);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                players.add(getPlayerInfoFromDb(set));
            }
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return players;
    }

    @Override
    public Player getPlayer(String name) {
        Player player = null;
        try {
            PreparedStatement statement = sql("select * from players where name=?");
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                player = getPlayerInfoFromDb(set);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return player;
    }

    @Override
    public void addPlayer(Player player) {
        try {
            String sql = "insert into players(name,age,nationality,club,position) values (?,?,?,?,?)";
            PreparedStatement statement = sql(sql);
            fillPlayerInfo(player, statement);
            statement.executeUpdate();
            statement.close();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new RTSQLIntegrityConstraintViolationException(e.getMessage());
        }
        catch (SQLException e) {
            throw new RuntimeSqlException(e.getMessage());
        }
    }

    @Override
    public void updatePlayer(long id, Player player) {
        try {
            PreparedStatement sql = sql("update players set name=?,age=?,nationality=?,club=?,position=? where player_id=?");
            fillPlayerInfo(player,sql);
            sql.setLong(6,id);
            sql.executeUpdate();
            sql.close();
        } catch (SQLException e) {
            throw new RuntimeSqlException(e.getMessage());
        }

    }

    private void fillPlayerInfo(Player player, PreparedStatement statement) throws SQLException {
        statement.setString(1, player.getName());
        statement.setInt(2, player.getAge());
        statement.setString(3, player.getNationality());
        statement.setString(4, player.getClub());
        statement.setString(5, player.getPosition());
    }

    private PreparedStatement sql(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    private static Player getPlayerInfoFromDb(ResultSet res) throws SQLException {
        return new Player(res.getInt("player_id"),
                res.getString("name"),
                res.getInt("age"),
                res.getString("nationality"),
                res.getString("club"),
                res.getString("position"));
    }
}
