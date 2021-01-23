package com.markokramar.emilfrey.resource;

import com.markokramar.emilfrey.dao.CarCategoriesDao;
import com.markokramar.emilfrey.model.CarCategory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("car_categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarCategoriesResource {

    @Inject
    private CarCategoriesDao carCategoriesDao;

    @GET
    public Response getAll() {
        return Response.ok(carCategoriesDao.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getCarCategory(@PathParam("id") Long id) {
        CarCategory carCategory = carCategoriesDao.findById(id);
        return Response.ok(carCategory).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, CarCategory carCategory) {
        CarCategory carCategoryToUpdate = carCategoriesDao.findById(id);
        carCategoryToUpdate.setCategoryName(carCategory.getCategoryName());
        carCategoriesDao.update(carCategoryToUpdate);
        return Response.ok().build();
    }

    @POST
    public Response create(CarCategory carCategory) {
        carCategoriesDao.create(carCategory);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        CarCategory carCategoryToDelete = carCategoriesDao.findById(id);
        carCategoriesDao.delete(carCategoryToDelete);
        return Response.ok().build();
    }
}
