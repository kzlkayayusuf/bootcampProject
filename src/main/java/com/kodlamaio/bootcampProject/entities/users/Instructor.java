package com.kodlamaio.bootcampProject.entities.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kodlamaio.bootcampProject.entities.bootcamps.Bootcamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instructors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends User {

	@Column(name = "companyName")
	private String companyName;

	@OneToMany(mappedBy = "instructor")
	private	List<Bootcamp> bootcamps;

//	@OneToOne
//	@MapsId
//	private User user;
}
