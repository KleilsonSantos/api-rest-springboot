package br.com.apicrudspring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.apicrudspring.model.ContactModel;
import br.com.apicrudspring.repositories.ContactRepository;
import br.com.apicrudspring.services.encrypt.EncryptingPasswordService;

@Service
public class ContactService {

	private final ContactRepository contactRepository;
	private final EncryptingPasswordService encrypt;

	public ContactService(ContactRepository contactRepository, EncryptingPasswordService encrypt) {
		this.contactRepository = contactRepository;
		this.encrypt = encrypt;
	}

	@Transactional
	public List<ContactModel> findAll() {
		return contactRepository.findAll();
	}

	@Transactional
	public ContactModel createNewContact(ContactModel entityContactModel) {
		entityContactModel.setPassword(encrypt.encryptPassword(entityContactModel.getPassword()));
		return contactRepository.save(entityContactModel);
	}

	@Transactional
	public ResponseEntity<ContactModel> findContactById(Long id) {
		ContactModel contactModel = contactRepository.findById(id).get();
		return ResponseEntity.ok(contactModel);
	}

	@Transactional
	public ResponseEntity<ContactModel> updateContactById(long id, ContactModel contact) {
		return contactRepository.findById(id).map(update -> {
			update.setName(contact.getName());
			update.setEmail(contact.getEmail());
			update.setAge(contact.getAge());
			update.setPassword(encrypt.encryptPassword(update.getPassword()));
			ContactModel updated = contactRepository.save(update);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@Transactional
	public ResponseEntity<Object> deleteContactById(Long id) {
		return contactRepository.findById(id)
				.map(delete -> {
					contactRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
