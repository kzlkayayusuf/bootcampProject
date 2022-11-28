package com.kodlamaio.bootcampProject.business.requests.users.instructors;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.kodlamaio.bootcampProject.business.constants.ValidationMessages;
import com.kodlamaio.bootcampProject.business.requests.users.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateInstructorRequest extends UserRequest {
	@NotBlank(message = ValidationMessages.Instructor.CompanyBlank)
	@Length(min = 2, max = 50, message = ValidationMessages.Instructor.CompanyValid)
	private String companyName;

}
