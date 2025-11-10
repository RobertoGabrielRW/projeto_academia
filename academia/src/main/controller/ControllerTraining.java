package com.exemplo.training.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class Training {
    private int id;
    private String nome;

    // Construtores, getters e setters
    public Training() {}
    public Training(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}

@Path("/training")
public class ControllerTraining {
    
    private final Map<Integer, Training> store = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTreinos() {
        return Response.ok(new ArrayList<>(store.values())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
    public Response criarTreino(Training treino) {
        int id = idCounter.getAndIncrement();
        treino.setId(id);
        store.put(id, treino);
        return Response.status(Response.Status.CREATED).entity(treino).build();
    }