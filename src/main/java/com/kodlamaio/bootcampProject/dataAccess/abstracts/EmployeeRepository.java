package com.kodlamaio.bootcampProject.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.users.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Optional<Employee> findByName(String name);
}
