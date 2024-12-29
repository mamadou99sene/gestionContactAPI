package bdk.sn.testbdk;

import bdk.sn.testbdk.metiers.ContactService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends Application {
    @EJB
    private ContactService contactService;

}
