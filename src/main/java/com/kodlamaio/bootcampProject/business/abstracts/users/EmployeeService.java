package com.kodlamaio.bootcampProject.business.abstracts.users;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.users.employees.CreateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.requests.users.employees.UpdateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.responses.users.employees.CreateEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.users.employees.GetAllEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.users.employees.GetEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.users.employees.UpdateEmployeeResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

public interface EmployeeService {
	
	DataResult<List<GetAllEmployeeResponse>> getAll();

	DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest createEmployeeRequest);

	DataResult<GetEmployeeResponse> getByName(String name);

	DataResult<GetEmployeeResponse> getById(int id);

	Result deleteById(int id);

	DataResult<List<GetAllEmployeeResponse>> deleteAll();

	DataResult<UpdateEmployeeResponse> update(UpdateEmployeeRequest updateEmployeeRequest);

}
