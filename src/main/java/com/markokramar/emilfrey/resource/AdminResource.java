package com.markokramar.emilfrey.resource;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Path("admin")
public class AdminResource {
    @Context
    ServletContext servletContext;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream viewAdminHome() throws Exception {
        File adminTemplateFile = new File(servletContext.getRealPath("/static/templates/admin.html"));
        return new FileInputStream(adminTemplateFile);
    }

}
