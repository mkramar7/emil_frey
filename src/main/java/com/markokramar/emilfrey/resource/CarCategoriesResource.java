package com.markokramar.emilfrey.resource;

import com.markokramar.emilfrey.model.CarCategory;
import com.markokramar.emilfrey.service.CarCategoriesService;

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
    private CarCategoriesService carCategoriesService;

    @GET
    public Response getAll(@QueryParam("name") String name) {
        return Response.ok(name == null || name.isEmpty() ?
                carCategoriesService.getAll() : carCategoriesService.getAll(name)).build();
    }

    @GET
    @Path("{id}")
    public Response getCarCategory(@PathParam("id") Long id) {
        CarCategory carCategory = carCategoriesService.findById(id);
        return Response.ok(carCategory).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, CarCategory carCategory) {
        CarCategory carCategoryToUpdate = carCategoriesService.findById(id);
        carCategoryToUpdate.setCategoryName(carCategory.getCategoryName());
        carCategoriesService.update(carCategoryToUpdate);
        return Response.ok().build();
    }

    @POST
    public Response create(CarCategory carCategory) {
        carCategoriesService.create(carCategory);
        return Response.ok(carCategory).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        CarCategory carCategoryToDelete = carCategoriesService.findById(id);
        carCategoriesService.delete(carCategoryToDelete);
        return Response.ok().build();
    }
}
