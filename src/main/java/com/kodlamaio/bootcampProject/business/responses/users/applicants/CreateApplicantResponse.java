package com.kodlamaio.bootcampProject.business.responses.users.applicants;

import com.kodlamaio.bootcampProject.business.responses.users.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicantResponse extends UserResponse {
	private int id;
	private String about;

}
