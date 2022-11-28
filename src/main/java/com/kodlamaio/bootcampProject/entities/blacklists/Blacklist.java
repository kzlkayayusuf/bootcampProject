package com.kodlamaio.bootcampProject.entities.blacklists;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kodlamaio.bootcampProject.entities.users.Applicant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "blacklists")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blacklist {

	// id,applicantId,date,reason
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "date")
	private LocalDate date;
	@Column(name = "reason")
	private String reason;

	@ManyToOne
	@JoinColumn(name = "applicantId")
	private Applicant applicant;

}
