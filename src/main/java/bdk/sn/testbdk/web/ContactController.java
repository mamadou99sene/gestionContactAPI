package bdk.sn.testbdk.web;

import bdk.sn.testbdk.entities.Contact;
import bdk.sn.testbdk.metiers.ContactService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactController {

    @EJB
    private ContactService contactService;

    @POST
    public Response createContact(Contact contact) {
        Contact created = contactService.createContact(contact);
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") Long id) {
        Contact contact = contactService.getContact(id);
        if (contact == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(contact).build();
    }

    @GET
    public Response getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return Response.ok(contacts).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") Long id, Contact contact) {
        contact.setId(id);
        Contact updated = contactService.updateContact(contact);
        return Response.ok(updated).build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") Long id) {
        contactService.deleteContact(id);
        return Response.noContent().build();
    }
}