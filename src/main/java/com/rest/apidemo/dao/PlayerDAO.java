package com.rest.apidemo.dao;

import com.rest.apidemo.model.Player;

import java.util.List;

public interface PlayerDAO {
    List<Player> getPlayers();
    List<Player> getPlayers(String club);
    Player getPlayer(String name);
}
