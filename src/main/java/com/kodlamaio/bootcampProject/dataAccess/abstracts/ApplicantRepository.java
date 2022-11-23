package com.kodlamaio.bootcampProject.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.users.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
	Optional<Applicant> findByName(String name);
}
