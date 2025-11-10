package main.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.PathParam;
import java.util.List;
import java.util.ArrayList;

@Path("/trainings")
public class ControllerTraining {

    private List<Training> trainings = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        return Response.ok(trainings).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") long id) {
        for (Training training :trainings) {
            if (training.getId() == id) {
                return Response.ok(training).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Training training) {
        trainings.add(training);
        return Response.status(Response.Status.CREATED).entity(training).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Training updatedTraining) {
        for (Training training : trainings) {
            if (training.getId() == id) {
                training.setName(updatedTraining.getName());
                training.setDescription(updatedTraining.getDescription());
                return Response.ok(training).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        for (Training training : trainings) {
            if (training.getId() == id) {
                trainings.remove(training);
                return Response.noContent().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}