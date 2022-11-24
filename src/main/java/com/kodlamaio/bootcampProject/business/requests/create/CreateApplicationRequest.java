package com.kodlamaio.bootcampProject.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicationRequest {
	private int userId;
	private int bootcampId;
	private int state;
}
