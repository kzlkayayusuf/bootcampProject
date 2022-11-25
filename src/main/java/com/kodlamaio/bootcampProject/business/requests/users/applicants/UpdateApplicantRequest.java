package com.kodlamaio.bootcampProject.business.requests.users.applicants;

import com.kodlamaio.bootcampProject.business.requests.users.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicantRequest extends UserRequest {
	private int id;
	private String about;

}
