package com.kodlamaio.bootcampProject.business.requests.users.applicants;

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
public class UpdateApplicantRequest extends UserRequest {
	private int id;
	@NotBlank(message = ValidationMessages.Applicant.AboutBlank)
	@Length(min = 5, max = 50, message = ValidationMessages.Applicant.AboutValid)
	private String about;

}
