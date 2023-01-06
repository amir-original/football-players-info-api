package com.rest.apidemo;


import com.rest.apidemo.dao.exceptions.RTSQLIntegrityConstraintViolationException;
import com.rest.apidemo.dao.exceptions.RuntimeSqlException;
import com.rest.apidemo.model.Player;
import com.rest.apidemo.service.PlayerService;
import com.rest.apidemo.service.PlayerServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

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
    public List<Player> getPlayers() {
        return service.getPlayers();
    }

    @GET
    @Path("{name}")
    public Player getPlayer(@PathParam("name") String name) {
        return service.getPlayer(name);
    }

    @GET
    @Path("c/{club}")
    public List<Player> getPlayers(@PathParam("club") String club) {
        return service.getPlayers(club);
    }

    @POST
    @Consumes("application/json")
    public Response addPlayer(Player player) {
       Response response = Response.status(201).build();
        try {
            service.addPlayer(player);
        }catch (RTSQLIntegrityConstraintViolationException e){
            response = Response.status(409).build();
        }
        catch (RuntimeSqlException e) {
            response = Response.status(403).build();
        }
        return response;
    }

}
