package com.kodlamaio.bootcampProject.business.abstracts.users;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.users.instructors.CreateInstructorRequest;
import com.kodlamaio.bootcampProject.business.requests.users.instructors.UpdateInstructorRequest;
import com.kodlamaio.bootcampProject.business.responses.users.instructors.CreateInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.users.instructors.GetAllInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.users.instructors.GetInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.users.instructors.UpdateInstructorResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

public interface InstructorService {

	DataResult<List<GetAllInstructorResponse>> getAll();

	DataResult<CreateInstructorResponse> add(CreateInstructorRequest createInstructorRequest);

	DataResult<List<GetAllInstructorResponse>> getByName(String name);

	DataResult<GetInstructorResponse> getById(int id);

	Result deleteById(int id);

	DataResult<List<GetAllInstructorResponse>> deleteAll();

	DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest updateInstructorRequest);

	void checkIfInstructorExistById(int id);
}
