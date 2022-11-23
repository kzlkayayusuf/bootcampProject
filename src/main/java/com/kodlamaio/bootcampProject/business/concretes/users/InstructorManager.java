package com.kodlamaio.bootcampProject.business.concretes.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.users.InstructorService;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.create.CreateInstructorRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateInstructorRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateInstructorResponse;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.users.InstructorRepository;
import com.kodlamaio.bootcampProject.entities.users.Instructor;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class InstructorManager implements InstructorService {

	private InstructorRepository instructorRepository;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllInstructorResponse>> getAll() {
		List<Instructor> instructors = this.instructorRepository.findAll();
		List<GetAllInstructorResponse> instructorsResponse = instructors.stream().map(
				instructor -> this.modelMapperService.forResponse().map(instructor, GetAllInstructorResponse.class))
				.collect(Collectors.toList());

//		for (Instructor instructor : instructors) {
//			GetAllInstructorResponse responseItem = new GetAllInstructorResponse();
//			responseItem.setId(instructor.getId());
//			responseItem.setCompanyName(instructor.getCompanyName());
//			responseItem.setFirstName(instructor.getEmail());
//			responseItem.setLastName(instructor.getLastName());
//			responseItem.setEmail(instructor.getEmail());
//			responseItem.setPassword(instructor.getPassword());
//			instructorsResponse.add(responseItem);
//		}
		return new SuccessDataResult<List<GetAllInstructorResponse>>(instructorsResponse);
	}

	@Override
	public DataResult<CreateInstructorResponse> add(CreateInstructorRequest createInstructorRequest) {
		Instructor instructor = this.modelMapperService.forRequest().map(createInstructorRequest, Instructor.class);
		this.instructorRepository.save(instructor);

		CreateInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
				CreateInstructorResponse.class);
		return new SuccessDataResult<CreateInstructorResponse>(instructorResponse, Messages.InstructorCreated);
	}

	@Override
	public DataResult<GetInstructorResponse> getByName(String name) {
		Instructor instructor = instructorRepository.findByFirstName(name).get();
		GetInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
				GetInstructorResponse.class);
		return new SuccessDataResult<GetInstructorResponse>(instructorResponse);
	}

	@Override
	public DataResult<GetInstructorResponse> getById(int id) {
		Instructor instructor = this.instructorRepository.findById(id).get();
		GetInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
				GetInstructorResponse.class);
		return new SuccessDataResult<GetInstructorResponse>(instructorResponse);

	}

	@Override
	public Result deleteById(int id) {
//		Instructor instructor = instructorRepository.findById(id).get();
//		DeleteInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
//				DeleteInstructorResponse.class);
		instructorRepository.deleteById(id);
		return new SuccessResult(Messages.InstructorDeleted);
	}

	@Override
	public DataResult<List<GetAllInstructorResponse>> deleteAll() {
		List<Instructor> instructors = instructorRepository.findAll();
		List<GetAllInstructorResponse> instructorsResponse = instructors.stream().map(
				instructor -> this.modelMapperService.forResponse().map(instructor, GetAllInstructorResponse.class))
				.collect(Collectors.toList());
//		List<Instructor> instructors = instructorRepository.findAll();
//		List<GetAllInstructorResponse> instructorsResponse = new ArrayList<GetAllInstructorsResponse>();
//
//		for (Instructor instructor : instructors) {
//			GetAllInstructorsResponse responseItem = new GetAllInstructorsResponse();
//			responseItem.setId(instructor.getId());
//			responseItem.setCompanyName(instructor.getCompanyName());
//			responseItem.setFirstName(instructor.getEmail());
//			responseItem.setLastName(instructor.getLastName());
//			responseItem.setEmail(instructor.getEmail());
//			responseItem.setPassword(instructor.getPassword());
//			instructorsResponse.add(responseItem);
//		}
//		return instructorsResponse;
		return new SuccessDataResult<List<GetAllInstructorResponse>>(instructorsResponse);
	}

	@Override
	public DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest updateInstructorRequest) {
		Instructor instructor = this.modelMapperService.forRequest().map(updateInstructorRequest, Instructor.class);
		this.instructorRepository.save(instructor);

		UpdateInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
				UpdateInstructorResponse.class);
		return new SuccessDataResult<UpdateInstructorResponse>(instructorResponse, Messages.InstructorUpdated);
	}

}
