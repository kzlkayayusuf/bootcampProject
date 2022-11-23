package com.kodlamaio.bootcampProject.business.requests.update;

import com.kodlamaio.bootcampProject.business.requests.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest extends UserRequest  {
	private int id;
	private String position;
	
}
