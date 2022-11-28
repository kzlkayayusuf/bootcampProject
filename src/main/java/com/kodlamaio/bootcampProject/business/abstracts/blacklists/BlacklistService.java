package com.kodlamaio.bootcampProject.business.abstracts.blacklists;

import java.util.List;

import com.kodlamaio.bootcampProject.business.requests.blacklists.CreateBlacklistRequest;
import com.kodlamaio.bootcampProject.business.requests.blacklists.UpdateBlacklistRequest;
import com.kodlamaio.bootcampProject.business.responses.blacklists.CreateBlacklistResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklists.GetAllBlacklistResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklists.GetBlacklistResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklists.UpdateBlacklistResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

public interface BlacklistService {
	DataResult<List<GetAllBlacklistResponse>> getAll();

	DataResult<CreateBlacklistResponse> add(CreateBlacklistRequest createBlacklistRequest);

	DataResult<GetBlacklistResponse> getById(int id);

	Result deleteById(int id);

	DataResult<List<GetAllBlacklistResponse>> deleteAll();

	DataResult<UpdateBlacklistResponse> update(UpdateBlacklistRequest updateBlacklistRequest);

	void checkIfApplicantInBlacklist(int applicantId);
}
