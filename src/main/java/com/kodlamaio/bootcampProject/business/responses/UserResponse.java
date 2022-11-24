package com.kodlamaio.bootcampProject.business.responses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	private String firstName;
	private String lastName;
	private String email;
	//private String password;
	private LocalDate dateOfBirth;
	private String nationalityIdentity;
}
