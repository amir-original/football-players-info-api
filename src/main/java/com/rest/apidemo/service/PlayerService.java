package com.rest.apidemo.service;

import com.rest.apidemo.model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getPlayers();
    List<Player> getPlayers(String club);
    Player getPlayer(String name);

    void addPlayer(Player player);
}
