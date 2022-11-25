package com.kodlamaio.bootcampProject.business.requests.users.applicants;

import com.kodlamaio.bootcampProject.business.requests.users.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicantRequest extends UserRequest {
	private String about;
}
