package com.kodlamaio.bootcampProject.business.responses.users.employees;

import com.kodlamaio.bootcampProject.business.responses.users.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeResponse extends UserResponse {
	private String position;

}
