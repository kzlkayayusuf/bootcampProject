package com.kodlamaio.bootcampProject.business.requests.users.instructors;

import com.kodlamaio.bootcampProject.business.requests.users.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInstructorRequest extends UserRequest {
	private int id;
	private String companyName;

}
