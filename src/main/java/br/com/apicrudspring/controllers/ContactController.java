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

import br.com.apicrudspring.model.ContactModel;
import br.com.apicrudspring.services.ContactService;

@RestController
@RequestMapping(value = "/contacts")
public class ContactController {

	ContactService contactService;

	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	@GetMapping
	public ResponseEntity<List<ContactModel>> findAll() {
		List<ContactModel> listAllContacts = contactService.findAll();
		return ResponseEntity.ok(listAllContacts);
	}
	
	@PostMapping
	public ContactModel create(@RequestBody ContactModel contactModel){
		return contactService.createNewContact(contactModel); 
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContactModel> findById(@PathVariable Long id) {
		return contactService.findContactById(id);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ContactModel> updateContactById(@PathVariable Long id, @RequestBody ContactModel contactModel){
		return contactService.updateContactById(id, contactModel);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteContactById(@PathVariable Long id){
		return contactService.deleteContactById(id);
	}

}
