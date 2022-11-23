package com.kodlamaio.bootcampProject.business.abstracts.users;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.create.CreateApplicantRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateApplicantRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateApplicantResponse;
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
