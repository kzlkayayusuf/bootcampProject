package com.kodlamaio.bootcampProject.business.requests.delete;

import com.kodlamaio.bootcampProject.business.requests.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteApplicantRequest extends UserRequest {
	private int id;
	private String about;
	

}
