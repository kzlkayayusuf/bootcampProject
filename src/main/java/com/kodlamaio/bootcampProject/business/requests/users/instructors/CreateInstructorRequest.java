package com.kodlamaio.bootcampProject.business.requests.users.instructors;

import com.kodlamaio.bootcampProject.business.requests.users.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInstructorRequest extends UserRequest {
	private String companyName;

}
