package com.markokramar.emilfrey.resource;

import com.markokramar.emilfrey.service.CarsService;
import com.markokramar.emilfrey.model.Car;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        return Response.ok(car).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Car car) {
        Car carToUpdate = carsService.findById(id);
        carToUpdate.setManufacturer(car.getManufacturer());
        carToUpdate.setModel(car.getModel());
        carToUpdate.setCategory(car.getCategory());
        carToUpdate.setManufacturingDate(car.getManufacturingDate());
        carsService.update(carToUpdate);
        return Response.ok().build();
    }

    @POST
    public Response create(Car car) {
        carsService.create(car);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Car carToDelete = carsService.findById(id);
        carsService.delete(carToDelete);
        return Response.ok().build();
    }
}
