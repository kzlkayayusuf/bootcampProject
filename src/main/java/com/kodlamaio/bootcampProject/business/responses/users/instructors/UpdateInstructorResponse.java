package com.kodlamaio.bootcampProject.business.responses.users.instructors;

import com.kodlamaio.bootcampProject.business.responses.users.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInstructorResponse extends UserResponse {
	private int id;
	private String companyName;

}
