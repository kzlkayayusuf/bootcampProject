package com.kodlamaio.bootcampProject.business.concretes.applications;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.applications.ApplicationService;
import com.kodlamaio.bootcampProject.business.abstracts.blacklists.BlacklistService;
import com.kodlamaio.bootcampProject.business.abstracts.bootcamps.BootcampService;
import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.applications.CreateApplicationRequest;
import com.kodlamaio.bootcampProject.business.requests.applications.UpdateApplicationRequest;
import com.kodlamaio.bootcampProject.business.responses.applications.CreateApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.applications.GetAllApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.applications.GetApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.applications.UpdateApplicationResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.applications.ApplicationRepository;
import com.kodlamaio.bootcampProject.entities.applications.Application;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApplicationManager implements ApplicationService {

	private ApplicationRepository applicationRepository;
	private BootcampService bootcampService;
	private ApplicantService applicantService;
	private BlacklistService blacklistService;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllApplicationResponse>> getAll() {
		List<Application> applications = this.applicationRepository.findAll();
		List<GetAllApplicationResponse> applicationResponse = applications.stream().map(
				application -> this.modelMapperService.forResponse().map(application, GetAllApplicationResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllApplicationResponse>>(applicationResponse,
				Messages.Application.ListAll);
	}

	@Override
	public DataResult<CreateApplicationResponse> add(CreateApplicationRequest createApplicationRequest) {
		bootcampService.checkIfBootcampExistById(createApplicationRequest.getBootcampId());
		applicantService.checkIfApplicantExistsById(createApplicationRequest.getApplicantId());
		blacklistService.checkIfApplicantInBlacklist(createApplicationRequest.getApplicantId());
		checkIfUserHasApplication(createApplicationRequest.getApplicantId());
		bootcampService.checkIfBootcampIsActive(createApplicationRequest.getBootcampId());
		Application application = this.modelMapperService.forRequest().map(createApplicationRequest, Application.class);
		application.setId(0);
		this.applicationRepository.save(application);
		CreateApplicationResponse applicationResponse = this.modelMapperService.forResponse().map(application,
				CreateApplicationResponse.class);
		return new SuccessDataResult<CreateApplicationResponse>(applicationResponse, Messages.Application.Created);
	}

	@Override
	public DataResult<GetApplicationResponse> getById(int id) {
		checkIfApplicationNotExistsById(id);
		Application application = this.applicationRepository.findById(id);
		GetApplicationResponse applicationResponse = this.modelMapperService.forResponse().map(application,
				GetApplicationResponse.class);

		return new SuccessDataResult<GetApplicationResponse>(applicationResponse, Messages.Application.ListById);
	}

	@Override
	public Result deleteById(int id) {
		checkIfApplicationNotExistsById(id);
		this.applicationRepository.deleteById(id);

		return new SuccessResult(Messages.Application.Deleted);
	}

	@Override
	public DataResult<List<GetAllApplicationResponse>> deleteAll() {
		List<Application> applications = this.applicationRepository.findAll();
		List<GetAllApplicationResponse> applicationResponse = applications.stream().map(
				application -> this.modelMapperService.forResponse().map(application, GetAllApplicationResponse.class))
				.collect(Collectors.toList());
		this.applicationRepository.deleteAll();
		return new SuccessDataResult<List<GetAllApplicationResponse>>(applicationResponse,Messages.Application.AllDeleted);
	}

	@Override
	public DataResult<UpdateApplicationResponse> update(UpdateApplicationRequest updateApplicationRequest) {
		checkIfApplicationNotExistsById(updateApplicationRequest.getId());
		bootcampService.checkIfBootcampExistById(updateApplicationRequest.getBootcampId());
		applicantService.checkIfApplicantExistsById(updateApplicationRequest.getApplicantId());
		Application application = this.modelMapperService.forRequest().map(updateApplicationRequest, Application.class);
		this.applicationRepository.save(application);
		UpdateApplicationResponse applicationResponse = this.modelMapperService.forResponse().map(application,
				UpdateApplicationResponse.class);
		return new SuccessDataResult<UpdateApplicationResponse>(applicationResponse, Messages.Application.Updated);
	}

	@Override
	public Result findApplicationAndDeleteFromApplication(int applicantId) {
		Application application = applicationRepository.findApplicationByApplicantId(applicantId);
		if (applicationRepository.findById(applicantId) != null) {
			applicationRepository.deleteById(application.getId());

			return new SuccessResult(Messages.Blacklist.RemovedFromApplication);
		}

		return new SuccessResult(Messages.Blacklist.Blank);
	}

	public void checkIfApplicationNotExistsById(int id) {
		Application application = this.applicationRepository.findById(id);
		if (application == null) {
			throw new BusinessException(Messages.Application.NotExists);
		}
	}

	public void checkIfUserHasApplication(int userId) {
		if (applicationRepository.findApplicationByApplicantId(userId) !=null) {
			throw new BusinessException(Messages.Application.UserHasApplication);
		}
	}

}
