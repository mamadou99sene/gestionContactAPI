package bdk.sn.testbdk.metiers.impl;

import bdk.sn.testbdk.entities.Contact;
import bdk.sn.testbdk.metiers.ContactService;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
@Stateless
public class ContactServiceImpl implements ContactService {
    @PersistenceContext(unitName = "contactPU")
    private EntityManager entityManager;
    @Override
    public Contact createContact(Contact contact) {
       this.entityManager.persist(contact);
       return contact;
    }

    @Override
    public Contact getContact(Long id) {
        Contact contact = this.entityManager.find(Contact.class, id);
        return contact;
    }

    @Override
    public List<Contact> getAllContacts() {
        return this.entityManager.createQuery("SELECT c FROM Contact c", Contact.class).getResultList();
    }

    @Override
    public Contact updateContact(Contact contact) {

        Contact merged = this.entityManager.merge(contact);
        return merged;
    }

    @Override
    public void deleteContact(Long id) {
        Contact contact=getContact(id);
        if(contact!=null)
        {
            this.entityManager.remove(contact);
        }
    }
}
