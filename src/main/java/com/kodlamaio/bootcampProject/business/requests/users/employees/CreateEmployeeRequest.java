package com.kodlamaio.bootcampProject.business.requests.users.employees;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.kodlamaio.bootcampProject.business.constants.ValidationMessages;
import com.kodlamaio.bootcampProject.business.requests.users.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest extends UserRequest {
	@NotBlank(message = ValidationMessages.Employee.PositionBlank)
	@Length(min = 2, max = 50, message = ValidationMessages.Employee.PositionValid)
	private String position;

}
