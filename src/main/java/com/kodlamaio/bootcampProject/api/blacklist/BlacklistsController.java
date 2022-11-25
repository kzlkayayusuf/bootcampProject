package com.kodlamaio.bootcampProject.api.blacklist;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.bootcampProject.business.abstracts.blacklist.BlacklistService;
import com.kodlamaio.bootcampProject.business.requests.blacklist.CreateBlackListRequest;
import com.kodlamaio.bootcampProject.business.requests.blacklist.UpdateBlackListRequest;
import com.kodlamaio.bootcampProject.business.responses.blacklist.CreateBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklist.GetAllBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklist.GetBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklist.UpdateBlackListResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/blacklists")
public class BlacklistsController {

	private BlacklistService blacklistService;

	@GetMapping("/getall")
	public DataResult<List<GetAllBlackListResponse>> getAll() {
		return this.blacklistService.getAll();
	}

	@PostMapping("/add")
	public DataResult<CreateBlackListResponse> add(@RequestBody CreateBlackListRequest createBlackListRequest) {
		return this.blacklistService.add(createBlackListRequest);
	}

	@GetMapping("/getById/{id}")
	public DataResult<GetBlackListResponse> getById(@PathVariable int id) {
		return this.blacklistService.getById(id);
	}

	@DeleteMapping("/deleteById/{id}")
	public Result deleteById(@PathVariable int id) {
		return this.blacklistService.deleteById(id);
	}

	@DeleteMapping("/deleteAll")
	public DataResult<List<GetAllBlackListResponse>> deleteAll() {
		return this.blacklistService.deleteAll();
	}

	@PutMapping("/update")
	public DataResult<UpdateBlackListResponse> update(@RequestBody UpdateBlackListRequest updateBlackListRequest) {
		return this.blacklistService.update(updateBlackListRequest);
	}

}
