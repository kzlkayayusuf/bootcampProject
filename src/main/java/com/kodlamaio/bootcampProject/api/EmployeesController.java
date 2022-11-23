package com.kodlamaio.bootcampProject.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.bootcampProject.business.abstracts.EmployeeService;
import com.kodlamaio.bootcampProject.business.requests.create.CreateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateEmployeeRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.delete.DeleteEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllEmployeesResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetEmployeeResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateEmployeeResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
	
	private EmployeeService employeeService;
	
	@GetMapping("/getall")
    public List<GetAllEmployeesResponse> getAll() {
        return employeeService.getAll();
    }

    @PostMapping("/add")
    public CreateEmployeeResponse add(@RequestBody() CreateEmployeeRequest createEmployeeRequest) {
        return this.employeeService.add(createEmployeeRequest);
    }

    @GetMapping("/getByName/{name}")
    public GetEmployeeResponse getByName(@RequestParam String name) {
        return employeeService.getByName(name);
    }

    @GetMapping("/getById/{id}")
    public GetEmployeeResponse getById(@RequestParam int id) {
        return employeeService.getById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public DeleteEmployeeResponse deleteById(@RequestParam int id) {
        return employeeService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public List<GetAllEmployeesResponse> deleteAll() {
       return employeeService.deleteAll();
    }

    @PutMapping("/update")
    public UpdateEmployeeResponse update(@RequestBody() UpdateEmployeeRequest updateEmployeeRequest) {
        return employeeService.update(updateEmployeeRequest);
    }

}
