package br.com.apicrudspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apicrudspring.model.ContactModel;

@Repository
public interface ContactRepository extends JpaRepository<ContactModel, Long>{
	ContactModel findByEmail(String email);
}
