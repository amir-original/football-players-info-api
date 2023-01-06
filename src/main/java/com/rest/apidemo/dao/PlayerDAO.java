package com.rest.apidemo.dao;

import com.rest.apidemo.model.Player;

import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {
    List<Player> getPlayers();
    List<Player> getPlayers(String club);
    Player getPlayer(String name);

    void addPlayer(Player player);
    void updatePlayer(long id,Player player) throws SQLException;

    void deletePlayer(int id);

    Player getPlayer(int id);
}
