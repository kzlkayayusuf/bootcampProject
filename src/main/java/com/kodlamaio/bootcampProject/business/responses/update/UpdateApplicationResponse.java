package com.kodlamaio.bootcampProject.business.responses.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationResponse {
	
	private int id;
	private int userId;
	private int bootcampId;
	private int state;

}
