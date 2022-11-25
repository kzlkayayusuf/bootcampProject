package com.kodlamaio.bootcampProject.entities.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kodlamaio.bootcampProject.entities.blacklist.BlackList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "applicants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicant extends User {

	@Column(name = "about")
	private String about;

	@OneToMany(mappedBy = "applicant")
	private List<BlackList> blackList;

//	@OneToOne
//	@MapsId
//	private User user;
}
