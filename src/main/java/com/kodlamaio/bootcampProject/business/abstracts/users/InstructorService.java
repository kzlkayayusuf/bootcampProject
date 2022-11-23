package com.kodlamaio.bootcampProject.business.abstracts.users;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.create.CreateInstructorRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateInstructorRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateInstructorResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

public interface InstructorService {
	//List<GetAllInstructorsResponse> getAll();
	DataResult<List<GetAllInstructorResponse>> getAll();
	
	//CreateInstructorResponse add(CreateInstructorRequest createInstructorRequest);
	DataResult<CreateInstructorResponse> add(CreateInstructorRequest createInstructorRequest);
	
	//GetInstructorResponse getByName(String name);
	DataResult<GetInstructorResponse> getByName(String name);

	//GetInstructorResponse getById(int id);
	DataResult<GetInstructorResponse> getById(int id);

	//DeleteInstructorResponse deleteById(int id);
	Result deleteById(int id);
	
	DataResult<List<GetAllInstructorResponse>> deleteAll();

	//UpdateInstructorResponse update(UpdateInstructorRequest updateInstructorRequest);
	DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest updateInstructorRequest);
}
