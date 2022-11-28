package com.kodlamaio.bootcampProject.business.requests.users;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kodlamaio.bootcampProject.business.constants.Regexes;
import com.kodlamaio.bootcampProject.business.constants.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	@NotBlank(message = ValidationMessages.User.FirstNameBlank)
	@Length(min = 2, max = 50, message = ValidationMessages.User.FirstNameValid)
	private String firstName;
	@NotBlank(message = ValidationMessages.User.LastNameBlank)
	@Length(min = 2, max = 50, message = ValidationMessages.User.LastNameValid)
	private String lastName;
	@NotNull(message = ValidationMessages.User.DateOfBirthBlank)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateOfBirth;
	@NotBlank(message = ValidationMessages.User.NationalIdentityBlank)
	@Length(min = 11, max = 11, message = ValidationMessages.User.NationalIdentityValid)
	private String nationalityIdentity;
	@NotBlank(message = ValidationMessages.User.EmailBlank)
	@Email(regexp = Regexes.Email, message = ValidationMessages.User.EmailValid)
	private String email;
	@NotBlank(message = ValidationMessages.User.PasswordBlank)
	@Pattern(regexp = Regexes.Password, message = ValidationMessages.User.PasswordValid)
	private String password;
}
