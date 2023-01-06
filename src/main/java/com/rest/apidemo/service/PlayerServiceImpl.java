package com.rest.apidemo.service;

import com.rest.apidemo.dao.PlayerDAOImpl;
import com.rest.apidemo.model.Player;

import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    private final PlayerDAOImpl dao;

    public PlayerServiceImpl() {
        dao = new PlayerDAOImpl();
    }

    @Override
    public List<Player> getPlayers() {
        return dao.getPlayers();
    }

    @Override
    public List<Player> getPlayers(String club) {
        return dao.getPlayers(club);
    }

    @Override
    public Player getPlayer(String name) {
        return dao.getPlayer(name);
    }

    @Override
    public void addPlayer(Player player) {
        dao.addPlayer(player);
    }

    @Override
    public void updatePlayer(long id, Player player) {
        dao.updatePlayer(id,player);
    }

    @Override
    public void deletePlayer(int id) {
        dao.deletePlayer(id);
    }
}
