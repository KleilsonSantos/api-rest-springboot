package br.com.apicrudspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contact")
public class Contact {

	@Id
	@Column(name = "contact_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "contact_name", nullable = false, length = 15)
	private String name;

	@Column(name = "contact_email", nullable = false, unique = true, length = 30)
	private String email;

	@Column(name = "contact_age")
	private Integer age;
}
