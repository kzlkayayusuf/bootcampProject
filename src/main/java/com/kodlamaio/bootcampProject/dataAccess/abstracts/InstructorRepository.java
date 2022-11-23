package com.kodlamaio.bootcampProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.users.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
	
}
