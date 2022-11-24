package com.kodlamaio.bootcampProject.business.requests.create;

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
	private String dateStart;
	private String dateEnd;
}
