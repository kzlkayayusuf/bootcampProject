package com.kodlamaio.bootcampProject.business.abstracts.bootcamps;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.bootcamps.CreateBootcampRequest;
import com.kodlamaio.bootcampProject.business.requests.bootcamps.UpdateBootcampRequest;
import com.kodlamaio.bootcampProject.business.responses.bootcamps.CreateBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamps.GetAllBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamps.GetBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamps.UpdateBootcampResponse;
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
	
	void checkIfBootcampIsActive(int id);
	
	void checkIfBootcampExistById(int id);
}
