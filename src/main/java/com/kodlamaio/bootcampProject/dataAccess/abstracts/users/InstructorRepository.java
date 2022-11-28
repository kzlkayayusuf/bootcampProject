package com.kodlamaio.bootcampProject.dataAccess.abstracts.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.bootcampProject.entities.users.Instructor;
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
	
	List<Instructor> findByFirstName(String name);

	Instructor findByNationalityIdentity(String nationalityIdentity);

	Instructor findById(int id);
}
