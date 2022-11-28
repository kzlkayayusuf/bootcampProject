package com.kodlamaio.bootcampProject.entities.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kodlamaio.bootcampProject.entities.applications.Application;
import com.kodlamaio.bootcampProject.entities.blacklists.Blacklist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "applicants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Applicant extends User {

	@Column(name = "about")
	private String about;

	@OneToMany(mappedBy = "applicant")
	private List<Application> applications;

	@OneToMany(mappedBy = "applicant")
	private List<Blacklist> blacklists;

//	@OneToOne
//	@MapsId
//	private User user;
}
