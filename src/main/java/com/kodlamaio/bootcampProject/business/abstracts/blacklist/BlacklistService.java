package com.kodlamaio.bootcampProject.business.abstracts.blacklist;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.blacklist.CreateBlackListRequest;
import com.kodlamaio.bootcampProject.business.requests.blacklist.UpdateBlackListRequest;
import com.kodlamaio.bootcampProject.business.responses.blacklist.CreateBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklist.GetAllBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklist.GetBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklist.UpdateBlackListResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

public interface BlacklistService {
	DataResult<List<GetAllBlackListResponse>> getAll();

	DataResult<CreateBlackListResponse> add(CreateBlackListRequest createBlackListRequest);

	DataResult<GetBlackListResponse> getById(int id);

	Result deleteById(int id);

	DataResult<List<GetAllBlackListResponse>> deleteAll();

	DataResult<UpdateBlackListResponse> update(UpdateBlackListRequest updateBlackListRequest);
	
}
