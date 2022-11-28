package com.kodlamaio.bootcampProject.entities.applications;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kodlamaio.bootcampProject.entities.bootcamps.Bootcamp;
import com.kodlamaio.bootcampProject.entities.users.Applicant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "applications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application {

	// id,userId,bootcampId,state
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "state")
	private int state;

	@ManyToOne
	@JoinColumn(name = "applicantId")
	private Applicant applicant;

	@ManyToOne
	@JoinColumn(name = "bootcampId")
	private Bootcamp bootcamp;

}
