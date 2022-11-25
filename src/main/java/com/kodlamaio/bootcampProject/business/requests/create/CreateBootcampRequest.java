package com.kodlamaio.bootcampProject.business.requests.create;

import java.time.LocalDate;

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
	private LocalDate dateStart;
	private LocalDate dateEnd;
}
