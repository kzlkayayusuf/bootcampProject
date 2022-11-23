package com.kodlamaio.bootcampProject.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.EmployeeService;
import com.kodlamaio.bootcampProject.business.requests.create.CreateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.delete.DeleteEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllEmployeesResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateEmployeeResponse;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.EmployeeRepository;
import com.kodlamaio.bootcampProject.entities.users.Employee;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeManager implements EmployeeService {

	private EmployeeRepository employeeRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllEmployeesResponse> getAll() {
		List<Employee> employees = employeeRepository.findAll();
		List<GetAllEmployeesResponse> employeesResponse = new ArrayList<>();

		for (Employee employee : employees) {
			GetAllEmployeesResponse responseItem = new GetAllEmployeesResponse();
			responseItem.setId(employee.getId());
			responseItem.setPosition(employee.getPosition());
			responseItem.setFirstName(employee.getEmail());
			responseItem.setLastName(employee.getLastName());
			responseItem.setEmail(employee.getEmail());
			responseItem.setPassword(employee.getPassword());
			employeesResponse.add(responseItem);
		}
		return employeesResponse;
	}

	@Override
	public CreateEmployeeResponse add(CreateEmployeeRequest createEmployeeRequest) {
		Employee employee = this.modelMapperService.forRequest().map(createEmployeeRequest, Employee.class);
		this.employeeRepository.save(employee);

		CreateEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				CreateEmployeeResponse.class);
		return employeeResponse;
	}

	@Override
	public GetEmployeeResponse getByName(String name) {
		Employee employee = employeeRepository.findByName(name).get();
		GetEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				GetEmployeeResponse.class);
		return employeeResponse;
	}

	@Override
	public GetEmployeeResponse getById(int id) {
		Employee employee = employeeRepository.findById(id).get();
		GetEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				GetEmployeeResponse.class);
		return employeeResponse;
	}

	@Override
	public DeleteEmployeeResponse deleteById(int id) {
		Employee employee = employeeRepository.findById(id).get();
		DeleteEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				DeleteEmployeeResponse.class);
		employeeRepository.deleteById(id);
		return employeeResponse;
	}

	@Override
	public List<GetAllEmployeesResponse> deleteAll() {
		List<Employee> employees = employeeRepository.findAll();
		List<GetAllEmployeesResponse> employeesResponse = new ArrayList<GetAllEmployeesResponse>();

		for (Employee employee : employees) {
			GetAllEmployeesResponse responseItem = new GetAllEmployeesResponse();
			responseItem.setId(employee.getId());
			responseItem.setPosition(employee.getPosition());
			responseItem.setFirstName(employee.getEmail());
			responseItem.setLastName(employee.getLastName());
			responseItem.setEmail(employee.getEmail());
			responseItem.setPassword(employee.getPassword());
			employeesResponse.add(responseItem);
		}
		employeeRepository.deleteAll();
		return employeesResponse;
	}

	@Override
	public UpdateEmployeeResponse update(UpdateEmployeeRequest updateEmployeeRequest) {
		Employee employee = this.modelMapperService.forRequest().map(updateEmployeeRequest, Employee.class);
		this.employeeRepository.save(employee);

		UpdateEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				UpdateEmployeeResponse.class);
		return employeeResponse;
	}

}
