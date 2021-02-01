package com.markokramar.emilfrey.resource;

import com.markokramar.emilfrey.model.Car;
import com.markokramar.emilfrey.service.CarsService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarsResource {

    @Inject
    private CarsService carsService;

    @GET
    public Response getAll(@QueryParam("name") String name) {
        return Response.ok(name == null || name.isEmpty() ?
                carsService.getAll() : carsService.getAll(name)).build();
    }

    @GET
    @Path("{id}")
    public Response getCar(@PathParam("id") Long id) {
        Car car = carsService.findById(id);
        if (car == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Car not found for ID: " + id).build();
        }

        return Response.ok(car).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Car car) {
        if (id == null) {
            return Response.serverError().entity("ID cannot be blank").build();
        }

        if (car == null) {
            return Response.serverError().entity("Car param cannot be blank").build();
        }

        Car carToUpdate = carsService.findById(id);
        if (carToUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Car not found for ID: " + id).build();
        }

        carToUpdate.setManufacturer(car.getManufacturer());
        carToUpdate.setModel(car.getModel());
        carToUpdate.setCategory(car.getCategory());
        carToUpdate.setModelYear(car.getModelYear());
        carsService.update(carToUpdate);
        return Response.ok().build();
    }

    @POST
    public Response create(Car car) {
        carsService.create(car);
        return Response.ok(car).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        if (id == null) {
            return Response.serverError().entity("ID cannot be blank").build();
        }

        Car carToDelete = carsService.findById(id);
        if (carToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Car not found for ID: " + id).build();
        }

        carsService.delete(carToDelete);
        return Response.ok().build();
    }

    @DELETE
    public Response delete(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Response.serverError().entity("IDs need to be provided").build();
        }

        carsService.delete(ids);
        return Response.ok().build();
    }
}
