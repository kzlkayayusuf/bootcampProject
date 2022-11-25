package com.kodlamaio.bootcampProject.api.users;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.bootcampProject.business.abstracts.users.EmployeeService;
import com.kodlamaio.bootcampProject.business.requests.users.employees.CreateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.requests.users.employees.UpdateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.responses.users.employees.CreateEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.users.employees.GetAllEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.users.employees.GetEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.users.employees.UpdateEmployeeResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

	private EmployeeService employeeService;

	@GetMapping("/getall")
	public DataResult<List<GetAllEmployeeResponse>> getAll() {
		return this.employeeService.getAll();
	}

	@PostMapping("/add")
	public DataResult<CreateEmployeeResponse> add(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
		return this.employeeService.add(createEmployeeRequest);
	}

	@GetMapping("/getByName/{name}")
	public DataResult<GetEmployeeResponse> getByName(@PathVariable String name) {
		return employeeService.getByName(name);
	}

	@GetMapping("/getById/{id}")
	public DataResult<GetEmployeeResponse> getById(@PathVariable int id) {
		return this.employeeService.getById(id);
	}

	@DeleteMapping("/deleteById/{id}")
	public Result deleteById(@PathVariable int id) {
		return employeeService.deleteById(id);
	}

	@DeleteMapping("/deleteAll")
	public DataResult<List<GetAllEmployeeResponse>> deleteAll() {
		return employeeService.deleteAll();
	}

	@PutMapping("/update")
	public DataResult<UpdateEmployeeResponse> update(@RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
		return this.employeeService.update(updateEmployeeRequest);
	}

}
