package br.com.apicrudspring.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apicrudspring.model.Contact;
import br.com.apicrudspring.repositories.ContactRepository;

@RestController
@RequestMapping(path = "/contacts")
public class ContactController {

	ContactRepository contactRepository;

	public ContactController(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@GetMapping
	public List<?> findAll() {
		return contactRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public Contact findById(@PathVariable Long id) {
		Contact contact = contactRepository.findById(id).get();
		return contact;
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Contact contact) {
		return contactRepository.findById(id).map(update -> {
			update.setName(contact.getName());
			update.setEmail(contact.getEmail());
			update.setAge(contact.getAge());
			Contact updated = contactRepository.save(update);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Contact save(@RequestBody Contact cont) {
		Contact contact = contactRepository.save(cont);
		return contact;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return contactRepository.findById(id)
				.map(delete -> {
					contactRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
