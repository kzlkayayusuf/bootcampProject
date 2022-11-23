package com.kodlamaio.bootcampProject.business.abstracts.users;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.create.CreateApplicantRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateApplicantRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.delete.DeleteApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllApplicantsResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateApplicantResponse;

public interface ApplicantService {
	List<GetAllApplicantsResponse> getAll();

	   CreateApplicantResponse add(CreateApplicantRequest createApplicantRequest) throws Exception;

	   GetApplicantResponse getByName(String name);

	   GetApplicantResponse getById(int id);

	   DeleteApplicantResponse deleteById(int id);

	   List<GetAllApplicantsResponse> deleteAll();

	   UpdateApplicantResponse update(UpdateApplicantRequest updateApplicantRequest);

}
