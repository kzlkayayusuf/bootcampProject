package com.kodlamaio.bootcampProject.entities.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;

//	@OneToOne(mappedBy = "user")
//	@PrimaryKeyJoinColumn
//	private Instructor instructor;
//	
//	@OneToOne(mappedBy = "user")
//	@PrimaryKeyJoinColumn
//	private Employee employee;
//	
//	@OneToOne(mappedBy = "user")
//	@PrimaryKeyJoinColumn
//	private Applicant applicant;
}