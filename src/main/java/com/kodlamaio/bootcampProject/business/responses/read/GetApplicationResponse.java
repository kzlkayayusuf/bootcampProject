package com.kodlamaio.bootcampProject.business.responses.read;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetApplicationResponse {
	private int id;
	private int userId;
	private int bootcampId;
	private int state;
}
