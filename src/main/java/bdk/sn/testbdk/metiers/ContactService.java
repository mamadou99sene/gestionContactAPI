package bdk.sn.testbdk.metiers;

import bdk.sn.testbdk.entities.Contact;
import jakarta.ejb.Local;

import java.util.List;
@Local
public interface ContactService {
    Contact createContact(Contact contact);
    Contact getContact(Long id);
    List<Contact> getAllContacts();
    Contact updateContact(Contact contact);
    void deleteContact(Long id);
}
