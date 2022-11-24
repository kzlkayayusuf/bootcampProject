package com.kodlamaio.bootcampProject.business.concretes.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.create.CreateApplicantRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateApplicantRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateApplicantResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.users.ApplicantRepository;
import com.kodlamaio.bootcampProject.entities.users.Applicant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApplicantManager implements ApplicantService {

	private ApplicantRepository applicantRepository;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllApplicantResponse>> getAll() {
		List<Applicant> applicants = this.applicantRepository.findAll();
		List<GetAllApplicantResponse> applicantResponse = applicants.stream()
				.map(applicant -> this.modelMapperService.forResponse().map(applicant, GetAllApplicantResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllApplicantResponse>>(applicantResponse);
	}

	@Override
	public DataResult<CreateApplicantResponse> add(CreateApplicantRequest createApplicantRequest) {
		checkIfApplicantExistsByNationalityIdentity(createApplicantRequest.getNationalityIdentity());
		Applicant applicant = this.modelMapperService.forRequest().map(createApplicantRequest, Applicant.class);
		this.applicantRepository.save(applicant);

		CreateApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				CreateApplicantResponse.class);
		return new SuccessDataResult<CreateApplicantResponse>(applicantResponse, Messages.ApplicantCreated);
	}

	@Override
	public DataResult<GetApplicantResponse> getByName(String name) {
		checkIfApplicantNotExistsByFirstName(name);
		Applicant applicant = this.applicantRepository.findByFirstName(name);
		GetApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				GetApplicantResponse.class);
		return new SuccessDataResult<GetApplicantResponse>(applicantResponse);
	}

	@Override
	public DataResult<GetApplicantResponse> getById(int id) {
		checkIfApplicantNotExistsById(id);
		Applicant applicant = this.applicantRepository.findById(id);
		GetApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				GetApplicantResponse.class);
		return new SuccessDataResult<GetApplicantResponse>(applicantResponse);
	}

	@Override
	public Result deleteById(int id) {
		checkIfApplicantNotExistsById(id);
		this.applicantRepository.deleteById(id);
		return new SuccessResult(Messages.ApplicantDeleted);
	}

	@Override
	public DataResult<List<GetAllApplicantResponse>> deleteAll() {
		List<Applicant> applicants = this.applicantRepository.findAll();
		List<GetAllApplicantResponse> applicantResponse = applicants.stream()
				.map(applicant -> this.modelMapperService.forResponse().map(applicant, GetAllApplicantResponse.class))
				.collect(Collectors.toList());

		this.applicantRepository.deleteAll();
		return new SuccessDataResult<List<GetAllApplicantResponse>>(applicantResponse, Messages.AllApplicantDeleted);
	}

	@Override
	public DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest updateApplicantRequest) {
		checkIfApplicantNotExistsById(updateApplicantRequest.getId());
		Applicant applicant = this.modelMapperService.forRequest().map(updateApplicantRequest, Applicant.class);
		this.applicantRepository.save(applicant);

		UpdateApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				UpdateApplicantResponse.class);
		return new SuccessDataResult<UpdateApplicantResponse>(applicantResponse, Messages.ApplicantUpdated);
	}
	
	public void checkIfApplicantExistsByNationalityIdentity(String nationalityIdentity) {
		Applicant applicant=this.applicantRepository.findByNationalityIdentity(nationalityIdentity);
		if(applicant != null) {
			throw new BusinessException(Messages.IdentityExists);
		}
	}
	
	public void checkIfApplicantNotExistsById(int id) {
		Applicant applicant=this.applicantRepository.findById(id);
		if(applicant == null) {
			throw new BusinessException(Messages.IdNotExists);
		}
	}
	
	public void checkIfApplicantNotExistsByFirstName(String firstName) {
		Applicant applicant=this.applicantRepository.findByFirstName(firstName);
		if(applicant == null) {
			throw new BusinessException(Messages.NameNotExists);
		}
	}

}
