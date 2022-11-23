package com.kodlamaio.bootcampProject.business.abstracts;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.create.CreateInstructorRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateInstructorRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.delete.DeleteInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllInstructorsResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateInstructorResponse;

public interface InstructorService {
	List<GetAllInstructorsResponse> getAll();

	   CreateInstructorResponse add(CreateInstructorRequest createInstructorRequest);

	   GetInstructorResponse getByName(String name);

	   GetInstructorResponse getById(int id);

	   DeleteInstructorResponse deleteById(int id);

	   List<GetAllInstructorsResponse> deleteAll();

	   UpdateInstructorResponse update(UpdateInstructorRequest updateInstructorRequest);
}
