package com.kodlamaio.bootcampProject.business.responses.read;

import com.kodlamaio.bootcampProject.business.responses.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllApplicantResponse extends UserResponse {
	private int id;
	private String about;
	 
}
