package com.kodlamaio.bootcampProject.business.responses.blacklist;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBlackListResponse {
	private int id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private String reason;
	private int applicantId;
	private String userFirstName;
	private String userLastName;
}
