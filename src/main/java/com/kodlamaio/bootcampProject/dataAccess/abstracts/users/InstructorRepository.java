package com.kodlamaio.bootcampProject.dataAccess.abstracts.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.users.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
	Optional<Instructor> findByFirstName(String name);
}