package com.kodlamaio.bootcampProject.business.responses.applications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllApplicationResponse {
	private int id;
	private int userId;
	private int bootcampId;
	private int state;
	private String bootcampName;
	private String userFirstName;
	private String userLastName;
}
