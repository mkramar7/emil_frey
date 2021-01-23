package com.markokramar.emilfrey.resource;

import com.markokramar.emilfrey.dao.LeadsDao;
import com.markokramar.emilfrey.model.Lead;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("leads")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeadsResource {

    @Inject
    private LeadsDao leadsDao;

    @GET
    public Response getAll() {
        return Response.ok(leadsDao.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getLead(@PathParam("id") Long id) {
        Lead lead = leadsDao.findById(id);
        return Response.ok(lead).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Lead lead) {
        Lead leadToUpdate = leadsDao.findById(id);
        leadToUpdate.setFirstName(lead.getFirstName());
        leadToUpdate.setLastName(lead.getLastName());
        leadToUpdate.setCarsOfInterest(lead.getCarsOfInterest());
        leadsDao.update(leadToUpdate);
        return Response.ok().build();
    }

    @POST
    public Response create(Lead lead) {
        leadsDao.create(lead);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Lead leadToDelete = leadsDao.findById(id);
        leadsDao.delete(leadToDelete);
        return Response.ok().build();
    }
}
