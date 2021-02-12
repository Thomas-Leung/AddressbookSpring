package com.example.AddressbookSpring;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// this annotation is optional
//@RepositoryRestResource(collectionResourceRel = "addressBookApi", path = "addressBookApi")
public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
    AddressBook findById(long id);
}