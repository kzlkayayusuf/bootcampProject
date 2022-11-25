package com.kodlamaio.bootcampProject.business.responses.create;

import com.kodlamaio.bootcampProject.business.responses.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInstructorResponse extends UserResponse {
	private int id;
	private String companyName;

}
