package com.rest.apidemo;


import com.rest.apidemo.model.Player;
import com.rest.apidemo.service.PlayerService;
import com.rest.apidemo.service.PlayerServiceImpl;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import java.util.LinkedList;
import java.util.List;

@Path("players")
@Produces("application/json")
public class PlayerResource {

    private final PlayerService service;

    public PlayerResource() {
        service = new PlayerServiceImpl();
    }

    @GET
    public List<Player> getPlayers(){
        return service.getPlayers();
    }
    @GET
    @Path("{name}")
    public Player getPlayer(@PathParam("name") String name){
        return service.getPlayer(name);
    }

    @GET
    @Path("c/{club}")
    public List<Player> getPlayers(@PathParam("club") String club){
        return service.getPlayers(club);
    }

}
