package com.kodlamaio.bootcampProject.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
