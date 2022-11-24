package com.kodlamaio.bootcampProject.business.abstracts.applications;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.create.CreateBootcampRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateBootcampRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateBootcampResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

public interface BootcampService {
	DataResult<List<GetAllBootcampResponse>> getAll();

	DataResult<CreateBootcampResponse> add(CreateBootcampRequest createBootcampRequest);

	DataResult<GetBootcampResponse> getByName(String name);

	DataResult<GetBootcampResponse> getById(int id);

	Result deleteById(int id);

	DataResult<List<GetAllBootcampResponse>> deleteAll();

	DataResult<UpdateBootcampResponse> update(UpdateBootcampRequest updateBootcampRequest);
}
