package com.kodlamaio.bootcampProject.business.responses.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBootcampResponse {

	private int id;
	private String name;
	private int instructorId;
	private int state;
	private String dateStart;
	private String dateEnd;
}