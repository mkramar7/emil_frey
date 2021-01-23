package com.markokramar.emilfrey.resource;

import com.markokramar.emilfrey.dao.CarsDao;
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
    private CarsDao carsDao;

    @GET
    public Response getAll() {
        return Response.ok(carsDao.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getCar(@PathParam("id") Long id) {
        Car car = carsDao.findById(id);
        return Response.ok(car).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Car car) {
        Car carToUpdate = carsDao.findById(id);
        carToUpdate.setManufacturer(car.getManufacturer());
        carToUpdate.setModel(car.getModel());
        carToUpdate.setCategory(car.getCategory());
        carToUpdate.setManufacturingDate(car.getManufacturingDate());
        carsDao.update(carToUpdate);
        return Response.ok().build();
    }

    @POST
    public Response create(Car car) {
        carsDao.create(car);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Car carToDelete = carsDao.findById(id);
        carsDao.delete(carToDelete);
        return Response.ok().build();
    }
}
