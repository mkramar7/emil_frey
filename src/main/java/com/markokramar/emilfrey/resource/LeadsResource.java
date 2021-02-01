package com.markokramar.emilfrey.resource;

import com.markokramar.emilfrey.service.LeadsService;
import com.markokramar.emilfrey.model.Lead;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@RequestScoped
@Path("leads")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeadsResource {

    @Inject
    private LeadsService leadsService;

    @GET
    public Response getAll(@QueryParam("name") String name) {
        return Response.ok(name == null || name.isEmpty() ?
                leadsService.getAll() : leadsService.getAll(name)).build();
    }

    @GET
    @Path("{id}")
    public Response getLead(@PathParam("id") Long id) {
        Lead lead = leadsService.findById(id);
        return Response.ok(lead).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Lead lead) {
        Lead leadToUpdate = leadsService.findById(id);
        leadToUpdate.setFirstName(lead.getFirstName());
        leadToUpdate.setLastName(lead.getLastName());
        leadToUpdate.setCarsOfInterest(lead.getCarsOfInterest());
        leadsService.update(leadToUpdate);
        return Response.ok().build();
    }

    @POST
    public Response create(Lead lead) {
        leadsService.create(lead);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Lead leadToDelete = leadsService.findById(id);
        leadsService.delete(leadToDelete);
        return Response.ok().build();
    }

    @DELETE
    public Response delete(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new WebApplicationException(
                    Response.status(BAD_REQUEST).entity("Ids parameter to delete is mandatory").build()
            );
        }

        leadsService.delete(ids);
        return Response.ok().build();
    }
}
