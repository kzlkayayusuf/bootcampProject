package com.kodlamaio.bootcampProject.dataAccess.abstracts.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.users.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
	Applicant findByFirstName(String name);
	Applicant findByNationalityIdentity(String nationalityIdentity);
	Applicant findById(int id);
	//boolean existsByEmail(String email);
}
