package com.kodlamaio.bootcampProject.business.concretes.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.users.EmployeeService;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.create.CreateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateEmployeeResponse;
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
		return new SuccessDataResult<List<GetAllEmployeeResponse>>(employeesResponse);
	}

	@Override
	public DataResult<CreateEmployeeResponse> add(CreateEmployeeRequest createEmployeeRequest) {
		checkIfEmployeeExistsByNationalityIdentity(createEmployeeRequest.getNationalityIdentity());
		Employee employee = this.modelMapperService.forRequest().map(createEmployeeRequest, Employee.class);
		this.employeeRepository.save(employee);

		CreateEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				CreateEmployeeResponse.class);
		return new SuccessDataResult<CreateEmployeeResponse>(employeeResponse, Messages.EmployeeCreated);
	}

	@Override
	public DataResult<GetEmployeeResponse> getByName(String name) {
		checkIfEmployeeNotExistsByFirstName(name);
		Employee employee = this.employeeRepository.findByFirstName(name);
		GetEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				GetEmployeeResponse.class);

		return new SuccessDataResult<GetEmployeeResponse>(employeeResponse);
	}

	@Override
	public DataResult<GetEmployeeResponse> getById(int id) {
		checkIfEmployeeNotExistsById(id);
		Employee employee = this.employeeRepository.findById(id);
		GetEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				GetEmployeeResponse.class);

		return new SuccessDataResult<GetEmployeeResponse>(employeeResponse);
	}

	@Override
	public Result deleteById(int id) {
		checkIfEmployeeNotExistsById(id);
		this.employeeRepository.deleteById(id);
		return new SuccessResult(Messages.EmployeeDeleted);
	}

	@Override
	public DataResult<List<GetAllEmployeeResponse>> deleteAll() {
		List<Employee> employees = this.employeeRepository.findAll();
		List<GetAllEmployeeResponse> employeesResponse = employees.stream()
				.map(employee -> this.modelMapperService.forResponse().map(employee, GetAllEmployeeResponse.class))
				.collect(Collectors.toList());
		this.employeeRepository.deleteAll();
		return new SuccessDataResult<List<GetAllEmployeeResponse>>(employeesResponse, Messages.AllEmployeeDeleted);
	}

	@Override
	public DataResult<UpdateEmployeeResponse> update(UpdateEmployeeRequest updateEmployeeRequest) {
		checkIfEmployeeNotExistsById(updateEmployeeRequest.getId());
		Employee employee = this.modelMapperService.forRequest().map(updateEmployeeRequest, Employee.class);
		this.employeeRepository.save(employee);
		UpdateEmployeeResponse employeeResponse = this.modelMapperService.forResponse().map(employee,
				UpdateEmployeeResponse.class);

		return new SuccessDataResult<UpdateEmployeeResponse>(employeeResponse, Messages.EmployeeUpdated);
	}

	public void checkIfEmployeeExistsByNationalityIdentity(String nationalityIdentity) {
		Employee employee = this.employeeRepository.findByNationalityIdentity(nationalityIdentity);
		if (employee != null) {
			throw new BusinessException(Messages.IdentityExists);
		}
	}

	public void checkIfEmployeeNotExistsById(int id) {
		Employee employee = this.employeeRepository.findById(id);
		if (employee == null) {
			throw new BusinessException(Messages.IdNotExists);
		}
	}

	public void checkIfEmployeeNotExistsByFirstName(String firstName) {
		Employee employee = this.employeeRepository.findByFirstName(firstName);
		if (employee == null) {
			throw new BusinessException(Messages.NameNotExists);
		}
	}

}
