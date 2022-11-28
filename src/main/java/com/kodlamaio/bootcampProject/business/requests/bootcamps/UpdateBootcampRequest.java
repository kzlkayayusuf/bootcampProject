package com.kodlamaio.bootcampProject.business.requests.bootcamps;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kodlamaio.bootcampProject.business.constants.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBootcampRequest {
	private int id;

	@Min(value = 1, message = ValidationMessages.Bootcamp.InstructorIdBlank)
	private int instructorId;
	@NotBlank(message = ValidationMessages.Bootcamp.NameBlank)
	@Length(min = 3, max = 50, message = ValidationMessages.Bootcamp.NameValid)
	private String name;
	@Min(value = 1, message = ValidationMessages.Bootcamp.MinState)
	@Max(value = 2, message = ValidationMessages.Bootcamp.MaxState)
	private int state;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = ValidationMessages.Bootcamp.StartDateBlank)
	private LocalDate startDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = ValidationMessages.Bootcamp.EndDateBlank)
	private LocalDate endDate;

}
