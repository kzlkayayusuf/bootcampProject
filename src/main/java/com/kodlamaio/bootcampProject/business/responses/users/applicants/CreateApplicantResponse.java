package com.kodlamaio.bootcampProject.business.responses.users.applicants;

import com.kodlamaio.bootcampProject.business.responses.users.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicantResponse extends UserResponse {
	private String about;

}
