package com.kodlamaio.bootcampProject.dataAccess.abstracts.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.bootcampProject.entities.users.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findByFirstName(String name);

	Employee findByNationalityIdentity(String nationalityIdentity);

	Employee findById(int id);
}
