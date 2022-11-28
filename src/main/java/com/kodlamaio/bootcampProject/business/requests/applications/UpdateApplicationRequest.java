package com.kodlamaio.bootcampProject.business.requests.applications;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.kodlamaio.bootcampProject.business.constants.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationRequest {
	
	private int id;
	@Min(value = 1, message = ValidationMessages.Application.BootcampIdBlank)
	private int bootcampId;
	@Min(value = 1, message = ValidationMessages.Application.ApplicantIdBlank)
	private int applicantId;
	@Min(value = 1, message = ValidationMessages.Application.MinState)
	@Max(value = 4, message = ValidationMessages.Application.MaxState)
	private int state;
}
