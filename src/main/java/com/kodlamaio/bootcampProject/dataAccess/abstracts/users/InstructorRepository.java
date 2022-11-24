package com.kodlamaio.bootcampProject.dataAccess.abstracts.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.users.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
	Instructor findByFirstName(String name);

	Instructor findByNationalityIdentity(String nationalityIdentity);

	Instructor findById(int id);
}
