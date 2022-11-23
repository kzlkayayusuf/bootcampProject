package com.kodlamaio.bootcampProject.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.InstructorService;
import com.kodlamaio.bootcampProject.business.requests.create.CreateInstructorRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateInstructorRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.delete.DeleteInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllInstructorsResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateInstructorResponse;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.InstructorRepository;
import com.kodlamaio.bootcampProject.entities.users.Instructor;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class InstructorManager implements InstructorService {

	private InstructorRepository instructorRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllInstructorsResponse> getAll() {
		List<Instructor> instructors = instructorRepository.findAll();
		List<GetAllInstructorsResponse> instructorsResponse = new ArrayList<>();

		for (Instructor instructor : instructors) {
			GetAllInstructorsResponse responseItem = new GetAllInstructorsResponse();
			responseItem.setId(instructor.getId());
			responseItem.setCompanyName(instructor.getCompanyName());
			responseItem.setFirstName(instructor.getEmail());
			responseItem.setLastName(instructor.getLastName());
			responseItem.setEmail(instructor.getEmail());
			responseItem.setPassword(instructor.getPassword());
			instructorsResponse.add(responseItem);
		}
		return instructorsResponse;
	}

	@Override
	public CreateInstructorResponse add(CreateInstructorRequest createInstructorRequest) {
		Instructor instructor = this.modelMapperService.forRequest().map(createInstructorRequest, Instructor.class);
		this.instructorRepository.save(instructor);

		CreateInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
				CreateInstructorResponse.class);
		return instructorResponse;
	}

	@Override
	public GetInstructorResponse getByName(String name) {
		Instructor instructor = instructorRepository.findByName(name).get();
		GetInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
				GetInstructorResponse.class);
		return instructorResponse;
	}

	@Override
	public GetInstructorResponse getById(int id) {
		Instructor instructor = instructorRepository.findById(id).get();
		GetInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
				GetInstructorResponse.class);
		return instructorResponse;
	}

	@Override
	public DeleteInstructorResponse deleteById(int id) {
		Instructor instructor = instructorRepository.findById(id).get();
		DeleteInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
				DeleteInstructorResponse.class);
		instructorRepository.deleteById(id);
		return instructorResponse;
	}

	@Override
	public List<GetAllInstructorsResponse> deleteAll() {
		List<Instructor> instructors = instructorRepository.findAll();
		List<GetAllInstructorsResponse> instructorsResponse = new ArrayList<GetAllInstructorsResponse>();

		for (Instructor instructor : instructors) {
			GetAllInstructorsResponse responseItem = new GetAllInstructorsResponse();
			responseItem.setId(instructor.getId());
			responseItem.setCompanyName(instructor.getCompanyName());
			responseItem.setFirstName(instructor.getEmail());
			responseItem.setLastName(instructor.getLastName());
			responseItem.setEmail(instructor.getEmail());
			responseItem.setPassword(instructor.getPassword());
			instructorsResponse.add(responseItem);
		}
		instructorRepository.deleteAll();
		return instructorsResponse;
	}

	@Override
	public UpdateInstructorResponse update(UpdateInstructorRequest updateInstructorRequest) {
		Instructor instructor = this.modelMapperService.forRequest().map(updateInstructorRequest, Instructor.class);
		this.instructorRepository.save(instructor);

		UpdateInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
				UpdateInstructorResponse.class);
		return instructorResponse;
	}

}
