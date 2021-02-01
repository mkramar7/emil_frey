package com.markokramar.emilfrey.resource;

import com.markokramar.emilfrey.model.CarCategory;
import com.markokramar.emilfrey.service.CarCategoriesService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        if (carCategory == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Car category not found for ID: " + id).build();
        }

        return Response.ok(carCategory).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, CarCategory carCategory) {
        if (id == null) {
            return Response.serverError().entity("ID cannot be blank").build();
        }

        if (carCategory == null) {
            return Response.serverError().entity("Car category param cannot be blank").build();
        }

        CarCategory carCategoryToUpdate = carCategoriesService.findById(id);
        if (carCategoryToUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Car category not found for ID: " + id).build();
        }

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
        if (id == null) {
            return Response.serverError().entity("ID cannot be blank").build();
        }

        CarCategory carCategoryToDelete = carCategoriesService.findById(id);
        if (carCategoryToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Car category not found for ID: " + id).build();
        }

        carCategoriesService.delete(carCategoryToDelete);
        return Response.ok().build();
    }

    @DELETE
    public Response delete(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Response.serverError().entity("IDs need to be provided").build();
        }

        carCategoriesService.delete(ids);
        return Response.ok().build();
    }
}
