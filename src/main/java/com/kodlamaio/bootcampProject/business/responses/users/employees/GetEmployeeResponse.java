package com.kodlamaio.bootcampProject.business.responses.users.employees;

import com.kodlamaio.bootcampProject.business.responses.users.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeResponse extends UserResponse {
	private int id;
	private String position;

}
