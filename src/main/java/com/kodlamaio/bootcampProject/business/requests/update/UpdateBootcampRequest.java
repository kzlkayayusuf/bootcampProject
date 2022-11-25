package com.kodlamaio.bootcampProject.business.requests.update;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBootcampRequest {
	private int id;
	private String name;
	private int instructorId;
	private int state;
	private LocalDate dateStart;
	private LocalDate dateEnd;

}
