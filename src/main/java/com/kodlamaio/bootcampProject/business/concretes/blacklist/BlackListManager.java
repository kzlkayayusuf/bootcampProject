package com.kodlamaio.bootcampProject.business.concretes.blacklist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.blacklist.BlacklistService;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.blacklist.CreateBlackListRequest;
import com.kodlamaio.bootcampProject.business.requests.blacklist.UpdateBlackListRequest;
import com.kodlamaio.bootcampProject.business.responses.blacklist.CreateBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklist.GetAllBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklist.GetBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklist.UpdateBlackListResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.blacklist.BlackListRepository;
import com.kodlamaio.bootcampProject.entities.blacklist.BlackList;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BlackListManager implements BlacklistService {

	private BlackListRepository blackListRepository;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllBlackListResponse>> getAll() {
		List<BlackList> blacklists = this.blackListRepository.findAll();
		List<GetAllBlackListResponse> blacklistResponse = blacklists.stream()
				.map(blacklist -> this.modelMapperService.forResponse().map(blacklist, GetAllBlackListResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllBlackListResponse>>(blacklistResponse);
	}

	@Override
	public DataResult<CreateBlackListResponse> add(CreateBlackListRequest createBlackListRequest) {

		BlackList blacklist = this.modelMapperService.forRequest().map(createBlackListRequest, BlackList.class);
		blacklist.setId(0);
		this.blackListRepository.save(blacklist);
		CreateBlackListResponse blacklistResponse = this.modelMapperService.forResponse().map(blacklist,
				CreateBlackListResponse.class);
		return new SuccessDataResult<CreateBlackListResponse>(blacklistResponse, Messages.BlacklistCreated);
	}

	@Override
	public DataResult<GetBlackListResponse> getById(int id) {
		checkIfBlacklistNotExistsById(id);
		BlackList blacklist = this.blackListRepository.findById(id);
		GetBlackListResponse blacklistResponse = this.modelMapperService.forResponse().map(blacklist,
				GetBlackListResponse.class);

		return new SuccessDataResult<GetBlackListResponse>(blacklistResponse);
	}

	@Override
	public Result deleteById(int id) {
		checkIfBlacklistNotExistsById(id);
		this.blackListRepository.deleteById(id);

		return new SuccessResult(Messages.BlacklistDeleted);
	}

	@Override
	public DataResult<List<GetAllBlackListResponse>> deleteAll() {
		List<BlackList> blacklists = this.blackListRepository.findAll();
		List<GetAllBlackListResponse> blacklistResponse = blacklists.stream()
				.map(blacklist -> this.modelMapperService.forResponse().map(blacklist, GetAllBlackListResponse.class))
				.collect(Collectors.toList());
		this.blackListRepository.deleteAll();
		return new SuccessDataResult<List<GetAllBlackListResponse>>(blacklistResponse);
	}

	@Override
	public DataResult<UpdateBlackListResponse> update(UpdateBlackListRequest updateBlackListRequest) {
		checkIfBlacklistNotExistsById(updateBlackListRequest.getId());
		BlackList blacklist = this.modelMapperService.forRequest().map(updateBlackListRequest, BlackList.class);
		this.blackListRepository.save(blacklist);
		UpdateBlackListResponse blacklistResponse = this.modelMapperService.forResponse().map(blacklist,
				UpdateBlackListResponse.class);
		return new SuccessDataResult<UpdateBlackListResponse>(blacklistResponse, Messages.BlacklistUpdated);
	}

	public void checkIfBlacklistNotExistsById(int id) {
		BlackList blacklist = this.blackListRepository.findById(id);
		if (blacklist == null) {
			throw new BusinessException(Messages.IdNotExists);
		}
	}

}
