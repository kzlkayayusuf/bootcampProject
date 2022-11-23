package com.kodlamaio.bootcampProject.business.responses.delete;

import com.kodlamaio.bootcampProject.business.responses.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteEmployeeResponse extends UserResponse {
	private int id;
	private String position;
	 
}
