package com.markokramar.emilfrey.resource;

import com.markokramar.emilfrey.model.Lead;
import com.markokramar.emilfrey.service.LeadsService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("leads")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeadsResource {

    @Inject
    private LeadsService leadsService;

    @GET
    public Response getAll(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) {
        boolean firstNameEmpty = firstName == null || firstName.isEmpty();
        boolean lastNameEmpty = lastName == null || lastName.isEmpty();
        if (firstNameEmpty && lastNameEmpty) {
            return Response.ok(leadsService.getAll()).build();
        }

        return Response.ok(leadsService.getAll(firstNameEmpty ? "" : firstName, lastNameEmpty ? "" : lastName)).build();
    }

    @GET
    @Path("{id}")
    public Response getLead(@PathParam("id") Long id) {
        Lead lead = leadsService.findById(id);
        if (lead == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Lead not found for ID: " + id).build();
        }

        return Response.ok(lead).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Lead lead) {
        if (id == null) {
            return Response.serverError().entity("ID cannot be blank").build();
        }

        if (lead == null) {
            return Response.serverError().entity("Lead param cannot be blank").build();
        }

        Lead leadToUpdate = leadsService.findById(id);
        if (leadToUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Lead not found for ID: " + id).build();
        }

        leadToUpdate.setFirstName(lead.getFirstName());
        leadToUpdate.setLastName(lead.getLastName());
        leadToUpdate.setCarsOfInterest(lead.getCarsOfInterest());
        leadsService.update(leadToUpdate);
        return Response.ok().build();
    }

    @POST
    public Response create(Lead lead) {
        leadsService.create(lead);
        return Response.ok(lead).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Lead leadToDelete = leadsService.findById(id);
        if (leadToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Lead not found for ID: " + id).build();
        }

        leadsService.delete(leadToDelete);
        return Response.ok().build();
    }

    @DELETE
    public Response delete(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Response.serverError().entity("IDs need to be provided").build();
        }

        leadsService.delete(ids);
        return Response.ok().build();
    }
}
