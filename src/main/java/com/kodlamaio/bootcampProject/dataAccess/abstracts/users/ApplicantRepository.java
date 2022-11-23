package com.kodlamaio.bootcampProject.dataAccess.abstracts.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.users.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
	Optional<Applicant> findByFirstName(String name);
	//boolean existsByEmail(String email);
}
