package com.kodlamaio.bootcampProject.entities.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="instructors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends User {
	
	@Column(name="campanyName")
	private String companyName;

//	@OneToOne
//	@MapsId
//	private User user;
}
