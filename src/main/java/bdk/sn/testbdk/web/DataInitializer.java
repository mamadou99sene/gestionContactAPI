package bdk.sn.testbdk.web;

import bdk.sn.testbdk.entities.Contact;
import bdk.sn.testbdk.metiers.ContactService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class DataInitializer {
    @EJB
    private ContactService contactService;
    @PostConstruct
    public void init()
    {
        if(contactService.getAllContacts().isEmpty())
        {
            Contact contact1=new Contact();
            contact1.setPrenom("Mamadou");
            contact1.setNom("SENE");
            contact1.setTelephone("778340335");
            Contact contact2=new Contact();
            contact2.setPrenom("Mouhamed");
            contact2.setNom("Ndione");
            contact2.setTelephone("778340335");
            contactService.createContact(contact1);
            this.contactService.createContact(contact2);
        }
    }
}
