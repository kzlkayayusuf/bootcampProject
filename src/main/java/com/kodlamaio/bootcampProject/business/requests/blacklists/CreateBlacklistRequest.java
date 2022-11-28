package com.kodlamaio.bootcampProject.business.requests.blacklists;

import java.time.LocalDate;

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
public class CreateBlacklistRequest {

	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = ValidationMessages.Blacklist.DateBlank)
	private LocalDate date;
	@NotBlank(message = ValidationMessages.Blacklist.ReasonBlank)
	@Length(min = 5, max = 50, message = ValidationMessages.Blacklist.ReasonValid)
	private String reason;
	@Min(value = 1, message = ValidationMessages.Blacklist.ApplicantIdBlank)
	private int applicantId;
}
