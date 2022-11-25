package com.kodlamaio.bootcampProject.business.responses.read;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBootcampResponse {

	private int id;
	private String name;
	private int instructorId;
	private int state;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateStart;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateEnd;
	private String instructorFirstName;
	private String insructorLastName;
}
