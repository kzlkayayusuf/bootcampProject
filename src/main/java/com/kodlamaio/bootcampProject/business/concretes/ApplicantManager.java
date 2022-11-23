package com.kodlamaio.bootcampProject.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.ApplicantService;
import com.kodlamaio.bootcampProject.business.requests.create.CreateApplicantRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateApplicantRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.delete.DeleteApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllApplicantsResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateApplicantResponse;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.ApplicantRepository;
import com.kodlamaio.bootcampProject.entities.users.Applicant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApplicantManager implements ApplicantService {

	private ApplicantRepository applicantRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllApplicantsResponse> getAll() {
		List<Applicant> applicants = applicantRepository.findAll();
		List<GetAllApplicantsResponse> applicantsResponse = new ArrayList<>();

		for (Applicant applicant : applicants) {
			GetAllApplicantsResponse responseItem = new GetAllApplicantsResponse();
			responseItem.setId(applicant.getId());
			responseItem.setAbout(applicant.getAbout());
			responseItem.setEmail(applicant.getEmail());
			responseItem.setFirstName(applicant.getFirstName());
			responseItem.setLastName(applicant.getLastName());
			responseItem.setPassword(applicant.getPassword());
			applicantsResponse.add(responseItem);
		}
		return applicantsResponse;
	}

	@Override
	public CreateApplicantResponse add(CreateApplicantRequest createApplicantRequest) {
		Applicant applicant = this.modelMapperService.forRequest().map(createApplicantRequest, Applicant.class);
		this.applicantRepository.save(applicant);

		CreateApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				CreateApplicantResponse.class);
		return applicantResponse;
	}

	@Override
	public GetApplicantResponse getByName(String name) {
		Applicant applicant = applicantRepository.findByName(name).get();
		GetApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				GetApplicantResponse.class);
		return applicantResponse;
	}

	@Override
	public GetApplicantResponse getById(int id) {
		Applicant applicant = applicantRepository.findById(id).get();
		GetApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				GetApplicantResponse.class);
		return applicantResponse;
	}

	@Override
	public DeleteApplicantResponse deleteById(int id) {
		Applicant applicant = applicantRepository.findById(id).get();
		DeleteApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				DeleteApplicantResponse.class);
		applicantRepository.deleteById(id);
		return applicantResponse;
	}

	@Override
	public List<GetAllApplicantsResponse> deleteAll() {
		List<Applicant> applicants = applicantRepository.findAll();
		List<GetAllApplicantsResponse> applicantsResponse = new ArrayList<GetAllApplicantsResponse>();

		for (Applicant applicant : applicants) {
			GetAllApplicantsResponse responseItem = new GetAllApplicantsResponse();
			responseItem.setId(applicant.getId());
			responseItem.setAbout(applicant.getAbout());
			responseItem.setEmail(applicant.getEmail());
			responseItem.setFirstName(applicant.getFirstName());
			responseItem.setLastName(applicant.getLastName());
			responseItem.setPassword(applicant.getPassword());
			applicantsResponse.add(responseItem);
		}
		applicantRepository.deleteAll();
		return applicantsResponse;
	}

	@Override
	public UpdateApplicantResponse update(UpdateApplicantRequest updateApplicantRequest) {
		Applicant applicant = this.modelMapperService.forRequest().map(updateApplicantRequest, Applicant.class);
		this.applicantRepository.save(applicant);

		UpdateApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				UpdateApplicantResponse.class);
		return applicantResponse;
	}

}
