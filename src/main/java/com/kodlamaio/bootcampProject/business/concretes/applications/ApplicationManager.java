package com.kodlamaio.bootcampProject.business.concretes.applications;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.BootcampService;
import com.kodlamaio.bootcampProject.business.abstracts.applications.ApplicationService;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.create.CreateApplicationRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateApplicationRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateApplicationResponse;
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
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllApplicationResponse>> getAll() {
		List<Application> applications = this.applicationRepository.findAll();
		List<GetAllApplicationResponse> applicationResponse = applications.stream().map(
				application -> this.modelMapperService.forResponse().map(application, GetAllApplicationResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllApplicationResponse>>(applicationResponse);
	}

	@Override
	public DataResult<CreateApplicationResponse> add(CreateApplicationRequest createApplicationRequest) {
		checkIfUserHasApplication(createApplicationRequest.getUserId());
		Application application = this.modelMapperService.forRequest().map(createApplicationRequest, Application.class);
		this.applicationRepository.save(application);
		CreateApplicationResponse applicationResponse = this.modelMapperService.forResponse().map(application,
				CreateApplicationResponse.class);
		return new SuccessDataResult<CreateApplicationResponse>(applicationResponse, Messages.ApplicationCreated);
	}

	@Override
	public DataResult<GetApplicationResponse> getById(int id) {
		checkIfApplicationNotExistsById(id);
		Application application = this.applicationRepository.findById(id);
		GetApplicationResponse applicationResponse = this.modelMapperService.forResponse().map(application,
				GetApplicationResponse.class);

		return new SuccessDataResult<GetApplicationResponse>(applicationResponse);
	}

	@Override
	public Result deleteById(int id) {
		checkIfApplicationNotExistsById(id);
		this.applicationRepository.deleteById(id);

		return new SuccessResult(Messages.ApplicationDeleted);
	}

	@Override
	public DataResult<List<GetAllApplicationResponse>> deleteAll() {
		List<Application> applications = this.applicationRepository.findAll();
		List<GetAllApplicationResponse> applicationResponse = applications.stream().map(
				application -> this.modelMapperService.forResponse().map(application, GetAllApplicationResponse.class))
				.collect(Collectors.toList());
		this.applicationRepository.deleteAll();
		return new SuccessDataResult<List<GetAllApplicationResponse>>(applicationResponse);
	}

	@Override
	public DataResult<UpdateApplicationResponse> update(UpdateApplicationRequest updateApplicationRequest) {
		checkIfApplicationNotExistsById(updateApplicationRequest.getId());
		Application application = this.modelMapperService.forRequest().map(updateApplicationRequest, Application.class);
		this.applicationRepository.save(application);
		UpdateApplicationResponse applicationResponse = this.modelMapperService.forResponse().map(application,
				UpdateApplicationResponse.class);
		return new SuccessDataResult<UpdateApplicationResponse>(applicationResponse, Messages.ApplicationUpdated);
	}

	public void checkIfApplicationNotExistsById(int id) {
		Application application = this.applicationRepository.findById(id);
		if (application == null) {
			throw new BusinessException(Messages.IdNotExists);
		}
	}

	public void checkIfUserHasApplication(int userId) {
		if (applicationRepository.findById(userId) != null) {
			throw new BusinessException(Messages.ApplicationUserHasApplication);
		}
	}

}
