package com.kodlamaio.bootcampProject.business.concretes.blacklists;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.applications.ApplicationService;
import com.kodlamaio.bootcampProject.business.abstracts.blacklists.BlacklistService;
import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.blacklists.CreateBlacklistRequest;
import com.kodlamaio.bootcampProject.business.requests.blacklists.UpdateBlacklistRequest;
import com.kodlamaio.bootcampProject.business.responses.blacklists.CreateBlacklistResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklists.GetAllBlacklistResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklists.GetBlacklistResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklists.UpdateBlacklistResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.blacklists.BlacklistRepository;
import com.kodlamaio.bootcampProject.entities.blacklists.Blacklist;

@Service
public class BlacklistManager implements BlacklistService {

	private BlacklistRepository blacklistRepository;
	private ModelMapperService modelMapperService;
	private ApplicantService applicantService;
	private ApplicationService applicationService;

	public BlacklistManager(BlacklistRepository blacklistRepository, ModelMapperService modelMapperService,
			ApplicantService applicantService, @Lazy ApplicationService applicationService) {
		this.blacklistRepository = blacklistRepository;
		this.modelMapperService = modelMapperService;
		this.applicantService = applicantService;
		this.applicationService = applicationService;
	}

	@Override
	public DataResult<List<GetAllBlacklistResponse>> getAll() {
		List<Blacklist> blacklists = this.blacklistRepository.findAll();
		List<GetAllBlacklistResponse> blacklistResponse = blacklists.stream()
				.map(blacklist -> this.modelMapperService.forResponse().map(blacklist, GetAllBlacklistResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllBlacklistResponse>>(blacklistResponse, Messages.Blacklist.ListAll);
	}

	@Override
	public DataResult<CreateBlacklistResponse> add(CreateBlacklistRequest createBlacklistRequest) {
		applicantService.checkIfApplicantExistsById(createBlacklistRequest.getApplicantId());
		checkIfApplicantInBlacklist(createBlacklistRequest.getApplicantId());
		Blacklist blacklist = this.modelMapperService.forRequest().map(createBlacklistRequest, Blacklist.class);
		blacklist.setId(0);
		this.blacklistRepository.save(blacklist);
		Result result = applicationService
				.findApplicationAndDeleteFromApplication(createBlacklistRequest.getApplicantId());
		CreateBlacklistResponse blacklistResponse = this.modelMapperService.forResponse().map(blacklist,
				CreateBlacklistResponse.class);
		String message = Messages.Blacklist.Created + result.getMessage();
		return new SuccessDataResult<CreateBlacklistResponse>(blacklistResponse, message);
	}

	@Override
	public DataResult<GetBlacklistResponse> getById(int id) {
		checkIfBlacklistNotExistsById(id);
		Blacklist blacklist = this.blacklistRepository.findById(id);
		GetBlacklistResponse blacklistResponse = this.modelMapperService.forResponse().map(blacklist,
				GetBlacklistResponse.class);

		return new SuccessDataResult<GetBlacklistResponse>(blacklistResponse, Messages.Blacklist.ListById);
	}

	@Override
	public Result deleteById(int id) {
		checkIfBlacklistNotExistsById(id);
		this.blacklistRepository.deleteById(id);

		return new SuccessResult(Messages.Blacklist.Deleted);
	}

	@Override
	public DataResult<List<GetAllBlacklistResponse>> deleteAll() {
		List<Blacklist> blacklists = this.blacklistRepository.findAll();
		List<GetAllBlacklistResponse> blacklistResponse = blacklists.stream()
				.map(blacklist -> this.modelMapperService.forResponse().map(blacklist, GetAllBlacklistResponse.class))
				.collect(Collectors.toList());
		this.blacklistRepository.deleteAll();
		return new SuccessDataResult<List<GetAllBlacklistResponse>>(blacklistResponse);
	}

	@Override
	public DataResult<UpdateBlacklistResponse> update(UpdateBlacklistRequest updateBlacklistRequest) {
		checkIfBlacklistNotExistsById(updateBlacklistRequest.getId());
		Blacklist blacklist = this.modelMapperService.forRequest().map(updateBlacklistRequest, Blacklist.class);
		this.blacklistRepository.save(blacklist);
		UpdateBlacklistResponse blacklistResponse = this.modelMapperService.forResponse().map(blacklist,
				UpdateBlacklistResponse.class);
		return new SuccessDataResult<UpdateBlacklistResponse>(blacklistResponse, Messages.Blacklist.Updated);
	}

	@Override
	public void checkIfApplicantInBlacklist(int id) {
		if (blacklistRepository.existsBlacklistByApplicantId(id)) {
			throw new BusinessException(Messages.Blacklist.ApplicantInBlacklist);
		}
	}

	public void checkIfBlacklistNotExistsById(int id) {
		Blacklist blacklist = this.blacklistRepository.findById(id);
		if (blacklist == null) {
			throw new BusinessException(Messages.Blacklist.NotExists);
		}
	}

}
