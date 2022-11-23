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
		List<Instructor> instructors= instructorRepository.findAll();
		List<GetAllInstructorsResponse> instructorsResponse=new ArrayList<>();
		
		for (Instructor instructor: instructors) {
			GetAllInstructorsResponse responseItem= new GetAllInstructorsResponse();
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
		Instructor instructor=this.modelMapperService.forRequest().map(createInstructorRequest,Instructor.class);
        this.instructorRepository.save(instructor);

        CreateInstructorResponse instructorResponse=this.modelMapperService.forResponse().map(instructor,CreateInstructorResponse.class);
        return instructorResponse;
	}

	@Override
	public GetInstructorResponse getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetInstructorResponse getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteInstructorResponse deleteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GetAllInstructorsResponse> deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateInstructorResponse update(UpdateInstructorRequest updateInstructorRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
