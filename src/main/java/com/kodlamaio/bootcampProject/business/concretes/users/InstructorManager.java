package com.kodlamaio.bootcampProject.business.concretes.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.users.InstructorService;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.users.instructors.CreateInstructorRequest;
import com.kodlamaio.bootcampProject.business.requests.users.instructors.UpdateInstructorRequest;
import com.kodlamaio.bootcampProject.business.responses.users.instructors.CreateInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.users.instructors.GetAllInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.users.instructors.GetInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.users.instructors.UpdateInstructorResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
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

		return new SuccessDataResult<List<GetAllInstructorResponse>>(instructorsResponse, Messages.Instructor.ListAll);
	}

	@Override
	public DataResult<CreateInstructorResponse> add(CreateInstructorRequest createInstructorRequest) {
		checkIfInstructorExistsByNationalityIdentity(createInstructorRequest.getNationalityIdentity());
		Instructor instructor = this.modelMapperService.forRequest().map(createInstructorRequest, Instructor.class);
		this.instructorRepository.save(instructor);

		CreateInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
				CreateInstructorResponse.class);
		return new SuccessDataResult<CreateInstructorResponse>(instructorResponse, Messages.Instructor.Created);
	}

	@Override
	public DataResult<List<GetAllInstructorResponse>> getByName(String name) {
		checkIfInstructorNotExistsByFirstName(name);
		List<Instructor> instructors = instructorRepository.findByFirstName(name);
		List<GetAllInstructorResponse> instructorResponse = instructors.stream()
				.map(instructor -> this.modelMapperService.forResponse().map(instructor, GetAllInstructorResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllInstructorResponse>>(instructorResponse,Messages.Instructor.ListByName);
	}

	@Override
	public DataResult<GetInstructorResponse> getById(int id) {
		checkIfInstructorNotExistsById(id);
		Instructor instructor = this.instructorRepository.findById(id);
		GetInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
				GetInstructorResponse.class);
		return new SuccessDataResult<GetInstructorResponse>(instructorResponse,Messages.Instructor.ListById);

	}

	@Override
	public Result deleteById(int id) {
		checkIfInstructorNotExistsById(id);
		this.instructorRepository.deleteById(id);
		return new SuccessResult(Messages.Instructor.Deleted);
	}

	@Override
	public DataResult<List<GetAllInstructorResponse>> deleteAll() {
		List<Instructor> instructors = instructorRepository.findAll();
		List<GetAllInstructorResponse> instructorsResponse = instructors.stream().map(
				instructor -> this.modelMapperService.forResponse().map(instructor, GetAllInstructorResponse.class))
				.collect(Collectors.toList());

		this.instructorRepository.deleteAll();
		return new SuccessDataResult<List<GetAllInstructorResponse>>(instructorsResponse,
				Messages.Instructor.AllDeleted);
	}

	@Override
	public DataResult<UpdateInstructorResponse> update(UpdateInstructorRequest updateInstructorRequest) {
		checkIfInstructorNotExistsById(updateInstructorRequest.getId());
		checkIfInstructorNotExistsByNationalityIdentity(updateInstructorRequest.getNationalityIdentity());
		Instructor instructor = this.modelMapperService.forRequest().map(updateInstructorRequest, Instructor.class);
		this.instructorRepository.save(instructor);

		UpdateInstructorResponse instructorResponse = this.modelMapperService.forResponse().map(instructor,
				UpdateInstructorResponse.class);
		return new SuccessDataResult<UpdateInstructorResponse>(instructorResponse, Messages.Instructor.Updated);
	}
	
	@Override
	public void checkIfInstructorExistById(int id) {
		if(!this.instructorRepository.existsById(id)) {
			throw new BusinessException(Messages.Instructor.NotExists);
		}
		
	}

	public void checkIfInstructorExistsByNationalityIdentity(String nationalityIdentity) {
		Instructor instructor = this.instructorRepository.findByNationalityIdentity(nationalityIdentity);
		if (instructor != null) {
			throw new BusinessException(Messages.Instructor.Exists);
		}
	}
	
	public void checkIfInstructorNotExistsByNationalityIdentity(String nationalityIdentity) {
		Instructor instructor = this.instructorRepository.findByNationalityIdentity(nationalityIdentity);
		if (instructor == null) {
			throw new BusinessException(Messages.Instructor.NotExists);
		}
	}

	public void checkIfInstructorNotExistsById(int id) {
		Instructor instructor = this.instructorRepository.findById(id);
		if (instructor == null) {
			throw new BusinessException(Messages.Instructor.NotExists);
		}
	}

	public void checkIfInstructorNotExistsByFirstName(String firstName) {
		List<Instructor> instructors = this.instructorRepository.findByFirstName(firstName);
		if (instructors.isEmpty()) {
			throw new BusinessException(Messages.Instructor.NameNotExists);
		}
	}

	

}
