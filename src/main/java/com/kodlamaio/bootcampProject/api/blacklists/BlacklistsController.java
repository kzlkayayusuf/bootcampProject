package com.kodlamaio.bootcampProject.api.blacklists;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.bootcampProject.business.abstracts.blacklists.BlacklistService;
import com.kodlamaio.bootcampProject.business.requests.blacklists.CreateBlacklistRequest;
import com.kodlamaio.bootcampProject.business.requests.blacklists.UpdateBlacklistRequest;
import com.kodlamaio.bootcampProject.business.responses.blacklists.CreateBlacklistResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklists.GetAllBlacklistResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklists.GetBlacklistResponse;
import com.kodlamaio.bootcampProject.business.responses.blacklists.UpdateBlacklistResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/blacklists")
public class BlacklistsController {

	private BlacklistService blacklistService;

	@GetMapping("/getall")
	public DataResult<List<GetAllBlacklistResponse>> getAll() {
		return this.blacklistService.getAll();
	}

	@PostMapping("/add")
	public DataResult<CreateBlacklistResponse> add(@Valid @RequestBody CreateBlacklistRequest createBlacklistRequest) {
		return this.blacklistService.add(createBlacklistRequest);
	}

	@GetMapping("/getById/{id}")
	public DataResult<GetBlacklistResponse> getById(@PathVariable int id) {
		return this.blacklistService.getById(id);
	}

	@DeleteMapping("/deleteById/{id}")
	public Result deleteById(@PathVariable int id) {
		return this.blacklistService.deleteById(id);
	}

	@DeleteMapping("/deleteAll")
	public DataResult<List<GetAllBlacklistResponse>> deleteAll() {
		return this.blacklistService.deleteAll();
	}

	@PutMapping("/update")
	public DataResult<UpdateBlacklistResponse> update(
			@Valid @RequestBody UpdateBlacklistRequest updateBlacklistRequest) {
		return this.blacklistService.update(updateBlacklistRequest);
	}

}
