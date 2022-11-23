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
		List<Employee> employees= employeeRepository.findAll();
		List<GetAllEmployeesResponse> employeesResponse=new ArrayList<>();
		
		for (Employee employee: employees) {
			GetAllEmployeesResponse responseItem= new GetAllEmployeesResponse();
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
		Employee employee=this.modelMapperService.forRequest().map(createEmployeeRequest,Employee.class);
        this.employeeRepository.save(employee);

        CreateEmployeeResponse employeeResponse=this.modelMapperService.forResponse().map(employee,CreateEmployeeResponse.class);
        return employeeResponse;
	}

	@Override
	public GetEmployeeResponse getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetEmployeeResponse getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteEmployeeResponse deleteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GetAllEmployeesResponse> deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateEmployeeResponse update(UpdateEmployeeRequest updateEmployeeRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
