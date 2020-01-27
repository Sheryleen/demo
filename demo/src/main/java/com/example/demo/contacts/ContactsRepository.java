package com.example.demo.contacts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contact, Integer> {
}


