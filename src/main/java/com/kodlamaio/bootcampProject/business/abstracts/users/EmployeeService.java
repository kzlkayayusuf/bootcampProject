package com.kodlamaio.bootcampProject.business.abstracts.users;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.create.CreateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.delete.DeleteEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllEmployeesResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateEmployeeResponse;

public interface EmployeeService {
	List<GetAllEmployeesResponse> getAll();

	   CreateEmployeeResponse add(CreateEmployeeRequest createEmployeeRequest);

	   GetEmployeeResponse getByName(String name);

	   GetEmployeeResponse getById(int id);

	   DeleteEmployeeResponse deleteById(int id);

	   List<GetAllEmployeesResponse> deleteAll();

	   UpdateEmployeeResponse update(UpdateEmployeeRequest updateEmployeeRequest);

}
