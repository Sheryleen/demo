package com.example.demo.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private final ContactsService contactsService;

    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @GetMapping
    public List<Contact> getAllContacts() {

        return this.contactsService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Contact getOneContact(@PathVariable int id) {
        Contact contact = contactsService.getOneContact(id).orElseThrow(IllegalArgumentException::new);
        return contact;
    }

    @PostMapping
    public Contact addOneContact(@RequestBody Contact newContact) {
        if (newContact.getEmail() == null || newContact.getName() == null) {
            throw new IllegalArgumentException("Not All fields are valid.  Go back and make sure all fields are valid" +
                    "before API call is made.");
        }
        return contactsService.addOneContact(newContact);
    }

    @PatchMapping
    public Contact updateOneContact(@RequestBody Contact updatedContact) {
        Contact contact = contactsService.getOneContact(updatedContact.getId()).orElseThrow(IllegalArgumentException::new);
        return contactsService.updateOneContact(updatedContact);
    }

    @DeleteMapping("/{id}")
    public String removeOneContact(@PathVariable int id) {
        Contact contact = contactsService.getOneContact(id).orElseThrow(IllegalArgumentException::new);
        return contactsService.removeOneContact(id);
    }
}