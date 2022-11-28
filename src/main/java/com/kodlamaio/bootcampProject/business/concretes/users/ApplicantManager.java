package com.kodlamaio.bootcampProject.business.concretes.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampProject.business.abstracts.users.EmployeeService;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.users.applicants.CreateApplicantRequest;
import com.kodlamaio.bootcampProject.business.requests.users.applicants.UpdateApplicantRequest;
import com.kodlamaio.bootcampProject.business.responses.users.applicants.CreateApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.users.applicants.GetAllApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.users.applicants.GetApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.users.applicants.UpdateApplicantResponse;
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
	private EmployeeService employeeService;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllApplicantResponse>> getAll() {
		List<Applicant> applicants = this.applicantRepository.findAll();
		List<GetAllApplicantResponse> applicantResponse = applicants.stream()
				.map(applicant -> this.modelMapperService.forResponse().map(applicant, GetAllApplicantResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllApplicantResponse>>(applicantResponse, Messages.Applicant.ListAll);
	}

	@Override
	public DataResult<CreateApplicantResponse> add(CreateApplicantRequest createApplicantRequest) {
		checkIfApplicantExistsByNationalityIdentity(createApplicantRequest.getNationalityIdentity());
		Applicant applicant = this.modelMapperService.forRequest().map(createApplicantRequest, Applicant.class);
		this.applicantRepository.save(applicant);

		CreateApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				CreateApplicantResponse.class);
		return new SuccessDataResult<CreateApplicantResponse>(applicantResponse, Messages.Applicant.Created);
	}

	@Override
	public DataResult<List<GetAllApplicantResponse>> getByName(String name) {
		checkIfApplicantNotExistsByFirstName(name);
		List<Applicant> applicants = this.applicantRepository.findByFirstName(name);
		List<GetAllApplicantResponse> applicantResponse = applicants.stream()
				.map(applicant -> this.modelMapperService.forResponse().map(applicant, GetAllApplicantResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllApplicantResponse>>(applicantResponse, Messages.Applicant.ListByName);
	}

	@Override
	public DataResult<GetApplicantResponse> getById(int id) {
		checkIfApplicantNotExistsById(id);
		Applicant applicant = this.applicantRepository.findById(id);
		GetApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				GetApplicantResponse.class);
		return new SuccessDataResult<GetApplicantResponse>(applicantResponse, Messages.Applicant.ListById);
	}

	@Override
	public Result deleteById(int id) {
		checkIfApplicantNotExistsById(id);
		this.applicantRepository.deleteById(id);
		return new SuccessResult(Messages.Applicant.Deleted);
	}

	@Override
	public DataResult<List<GetAllApplicantResponse>> deleteAll() {
		List<Applicant> applicants = this.applicantRepository.findAll();
		List<GetAllApplicantResponse> applicantResponse = applicants.stream()
				.map(applicant -> this.modelMapperService.forResponse().map(applicant, GetAllApplicantResponse.class))
				.collect(Collectors.toList());

		this.applicantRepository.deleteAll();
		return new SuccessDataResult<List<GetAllApplicantResponse>>(applicantResponse, Messages.Applicant.AllDeleted);
	}

	@Override
	public DataResult<UpdateApplicantResponse> update(UpdateApplicantRequest updateApplicantRequest) {
		checkIfApplicantNotExistsById(updateApplicantRequest.getId());
		checkIfApplicantNotExistsByNationalityIdentity(updateApplicantRequest.getNationalityIdentity());
		Applicant applicant = this.modelMapperService.forRequest().map(updateApplicantRequest, Applicant.class);
		this.applicantRepository.save(applicant);

		UpdateApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				UpdateApplicantResponse.class);
		return new SuccessDataResult<UpdateApplicantResponse>(applicantResponse, Messages.Applicant.Updated);
	}

	@Override
	public DataResult<GetApplicantResponse> beAnApplicant(String about, int id) {
		checkIfAlreadyAnApplicant(id);
		checkIfAboutValid(about);
		Applicant applicant = this.modelMapperService.forResponse().map(employeeService.getById(id).getData(),
				Applicant.class);
		applicant.setAbout(about);
		this.applicantRepository.beAnApplicant(about, id);
		GetApplicantResponse applicantResponse = this.modelMapperService.forResponse().map(applicant,
				GetApplicantResponse.class);

		return new SuccessDataResult<GetApplicantResponse>(applicantResponse, Messages.Applicant.BecameApplicant);
	}

	@Override
	public Result removeAnApplicant(int id) {
		checkIfApplicantNotExistsById(id);
		employeeService.checkIfUserIsEmployee(id);
		this.applicantRepository.removeApplicantFromApplication(id);
		this.applicantRepository.removeApplicantFromBlacklist(id);
		this.applicantRepository.removeAnApplicant(id);

		return new SuccessResult(Messages.Applicant.Deleted);
	}

	@Override
	public void checkIfApplicantExistsById(int id) {
		if (!applicantRepository.existsById(id)) {
			throw new BusinessException(Messages.Applicant.NotExists);
		}

	}

	public void checkIfApplicantExistsByNationalityIdentity(String nationalityIdentity) {
		Applicant applicant = this.applicantRepository.findByNationalityIdentity(nationalityIdentity);
		if (applicant != null) {
			throw new BusinessException(Messages.Applicant.Exists);
		}
	}
	
	public void checkIfApplicantNotExistsByNationalityIdentity(String nationalityIdentity) {
		Applicant applicant = this.applicantRepository.findByNationalityIdentity(nationalityIdentity);
		if (applicant == null) {
			throw new BusinessException(Messages.Applicant.NotExists);
		}
	}

	public void checkIfApplicantNotExistsById(int id) {
		Applicant applicant = this.applicantRepository.findById(id);
		if (applicant == null) {
			throw new BusinessException(Messages.Applicant.NotExists);
		}
	}

	public void checkIfAlreadyAnApplicant(int id) {
		Applicant applicant = this.applicantRepository.findById(id);
		if (applicant != null) {
			throw new BusinessException(Messages.Applicant.AlreadyApplicant);
		}
	}

	public void checkIfApplicantNotExistsByFirstName(String firstName) {
		List<Applicant> applicants = this.applicantRepository.findByFirstName(firstName);
		if (applicants.isEmpty()) {
			throw new BusinessException(Messages.Applicant.NameNotExists);
		}
	}

	private void checkIfAboutValid(String about) {
		if (about.length() <= 5 || about.length() >= 50) {
			throw new BusinessException(Messages.Applicant.AboutValid);
		}
	}

}
