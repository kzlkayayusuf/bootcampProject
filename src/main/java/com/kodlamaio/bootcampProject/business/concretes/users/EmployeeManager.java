package com.kodlamaio.bootcampProject.business.concretes.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.users.EmployeeService;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.users.employees.CreateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.requests.users.employees.UpdateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.responses.users.employees.CreateEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.users.employees.GetAllEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.users.employees.GetEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.users.employees.UpdateEmployeeResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.users.EmployeeRepository;
import com.kodlamaio.bootcampProject.entities.users.Employee;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeManager implements EmployeeService {

	private EmployeeRepository employeeRepository;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllEmployeeResponse>> getAll() {
		List<Employee> employees = this.employeeRepository.findAll();
		List<GetAllEmployeeResponse> employeesResponse = employees.stream()
				.map(employee -> this.modelMapperService.forResponse().map(employee, GetAllEmployeeResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllEmployeeResponse>>(employeesResponse,Messages.Employee.ListAll);
	}

	@Override
	public DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest createEmployeeRequest) {
		checkIfEmployeeExistsByNationalityIdentity(createEmployeeRequest.getNationalityIdentity());
		Employee employee = this.modelMapperService.forRequest().map(createEmployeeRequest, Employee.class);
		this.employeeRepository.save(employee);

		CreateEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				CreateEmployeeResponse.class);
		return new SuccessDataResult<CreateEmployeeResponse>(employeeResponse, Messages.Employee.Created);
	}

	@Override
	public DataResult<List<GetAllEmployeeResponse>> getByName(String name) {
		checkIfEmployeeNotExistsByFirstName(name);
		List<Employee> employees = this.employeeRepository.findByFirstName(name);
		List<GetAllEmployeeResponse> employeeResponse = employees.stream()
				.map(employee->this.modelMapperService.forResponse().map(employee, GetAllEmployeeResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllEmployeeResponse>>(employeeResponse,Messages.Employee.ListByName);
	}

	@Override
	public DataResult<GetEmployeeResponse> getById(int id) {
		checkIfEmployeeNotExistsById(id);
		Employee employee = this.employeeRepository.findById(id);
		GetEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				GetEmployeeResponse.class);

		return new SuccessDataResult<GetEmployeeResponse>(employeeResponse,Messages.Employee.ListById);
	}

	@Override
	public Result deleteById(int id) {
		checkIfEmployeeNotExistsById(id);
		this.employeeRepository.deleteById(id);
		return new SuccessResult(Messages.Employee.Deleted);
	}

	@Override
	public DataResult<List<GetAllEmployeeResponse>> deleteAll() {
		List<Employee> employees = this.employeeRepository.findAll();
		List<GetAllEmployeeResponse> employeesResponse = employees.stream()
				.map(employee -> this.modelMapperService.forResponse().map(employee, GetAllEmployeeResponse.class))
				.collect(Collectors.toList());
		this.employeeRepository.deleteAll();
		return new SuccessDataResult<List<GetAllEmployeeResponse>>(employeesResponse, Messages.Employee.AllDeleted);
	}

	@Override
	public DataResult<UpdateEmployeeResponse> update(UpdateEmployeeRequest updateEmployeeRequest) {
		checkIfEmployeeNotExistsById(updateEmployeeRequest.getId());
		checkIfEmployeeNotExistsByNationalityIdentity(updateEmployeeRequest.getNationalityIdentity());
		Employee employee = this.modelMapperService.forRequest().map(updateEmployeeRequest, Employee.class);
		this.employeeRepository.save(employee);
		UpdateEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				UpdateEmployeeResponse.class);

		return new SuccessDataResult<UpdateEmployeeResponse>(employeeResponse, Messages.Employee.Updated);
	}
	
	@Override
    public void checkIfUserIsEmployee(int id) {
        if (!this.employeeRepository.existsById(id)) {
            throw new BusinessException(Messages.Employee.NotAnEmployee);
        }
    }

	public void checkIfEmployeeExistsByNationalityIdentity(String nationalityIdentity) {
		Employee employee = this.employeeRepository.findByNationalityIdentity(nationalityIdentity);
		if (employee != null) {
			throw new BusinessException(Messages.Employee.Exists);
		}
	}
	
	public void checkIfEmployeeNotExistsByNationalityIdentity(String nationalityIdentity) {
		Employee employee = this.employeeRepository.findByNationalityIdentity(nationalityIdentity);
		if (employee == null) {
			throw new BusinessException(Messages.Employee.NotExists);
		}
	}

	public void checkIfEmployeeNotExistsById(int id) {
		Employee employee = this.employeeRepository.findById(id);
		if (employee == null) {
			throw new BusinessException(Messages.Employee.NotExists);
		}
	}

	public void checkIfEmployeeNotExistsByFirstName(String firstName) {
		List<Employee> employees = this.employeeRepository.findByFirstName(firstName);
		if (employees.isEmpty()) {
			throw new BusinessException(Messages.Employee.NameNotExists);
		}
	}



}
