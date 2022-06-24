package br.com.apicrudspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apicrudspring.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

}
