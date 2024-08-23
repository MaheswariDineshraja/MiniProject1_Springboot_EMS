package org.user.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="firstname")
	@NotBlank(message="Name is Mandatory")	
	private String firstname;
	
	@Column(name="lastname")
	@NotBlank(message="Name is Mandatory")	
	private String lastname;
	
	
	@Column(name="Email")
	@NotBlank(message="Email is Mandatory")
	@Email(message="Incorrect email format")
	private String email;
	
	
	

}
