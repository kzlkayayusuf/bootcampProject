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

	DataResult<List<GetAllInstructorResponse>> getAll();

	DataResult<CreateInstructorResponse> add(CreateInstructorRequest createInstructorRequest);

	DataResult<GetInstructorResponse> getByName(String name);

	DataResult<GetInstructorResponse> getById(int id);

	Result deleteById(int id);

	DataResult<List<GetAllInstructorResponse>> deleteAll();

	DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest updateInstructorRequest);
}
