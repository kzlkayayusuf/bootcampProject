package com.kodlamaio.bootcampProject.business.abstracts.applications;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.create.CreateApplicationRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateApplicationRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateApplicationResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

public interface ApplicationService {
	DataResult<List<GetAllApplicationResponse>> getAll();

	DataResult<CreateApplicationResponse> add(CreateApplicationRequest createApplicationRequest);

	DataResult<GetApplicationResponse> getById(int id);

	Result deleteById(int id);

	DataResult<List<GetAllApplicationResponse>> deleteAll();

	DataResult<UpdateApplicationResponse> update(UpdateApplicationRequest updateApplicationRequest);
}
