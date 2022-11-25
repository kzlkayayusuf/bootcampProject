package com.kodlamaio.bootcampProject.business.abstracts.users;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.users.applicants.CreateApplicantRequest;
import com.kodlamaio.bootcampProject.business.requests.users.applicants.UpdateApplicantRequest;
import com.kodlamaio.bootcampProject.business.responses.users.applicants.CreateApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.users.applicants.GetAllApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.users.applicants.GetApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.users.applicants.UpdateApplicantResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

public interface ApplicantService {

	DataResult<List<GetAllApplicantResponse>> getAll();

	DataResult<CreateApplicantResponse> add(CreateApplicantRequest createApplicantRequest);

	DataResult<GetApplicantResponse> getByName(String name);

	DataResult<GetApplicantResponse> getById(int id);

	Result deleteById(int id);

	DataResult<List<GetAllApplicantResponse>> deleteAll();

	DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest updateApplicantRequest);

}
