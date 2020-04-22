package org.vaadin.helloworld.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.helloworld.backend.entity.Contact;

/*
In spring data, by extending from JPA a repository and telling it what type were working with
we're working with a Contact and the contact has a Primary Key of type lung we get all the basic database operations
on that Contact for free so all the: save, delete, update, find operations we get by just defining this interface
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

}