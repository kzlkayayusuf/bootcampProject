package com.kodlamaio.bootcampProject.business.requests.create;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBootcampRequest {
	private String name;
	private int instructorId;
	private int state;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateStart;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateEnd;
}
