package com.kodlamaio.bootcampProject.dataAccess.abstracts.users;



import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.users.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByFirstName(String name);
	Employee findByNationalityIdentity(String nationalityIdentity);
	Employee findById(int id);
}
