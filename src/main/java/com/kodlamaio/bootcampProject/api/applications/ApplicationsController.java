package com.kodlamaio.bootcampProject.api.applications;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.bootcampProject.business.abstracts.applications.ApplicationService;
import com.kodlamaio.bootcampProject.business.requests.create.CreateApplicationRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateApplicationRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetApplicationResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateApplicationResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/applications")
public class ApplicationsController {
	private ApplicationService applicationService;

	@GetMapping("/getall")
	public DataResult<List<GetAllApplicationResponse>> getAll() {
		return this.applicationService.getAll();
	}

	@PostMapping("/add")
	public DataResult<CreateApplicationResponse> add(@RequestBody CreateApplicationRequest createApplicationRequest) {
		return this.applicationService.add(createApplicationRequest);
	}


	@GetMapping("/getById/{id}")
	public DataResult<GetApplicationResponse> getById(@PathVariable int id) {
		return this.applicationService.getById(id);
	}

	@DeleteMapping("/deleteById/{id}")
	public Result deleteById(@PathVariable int id) {
		return this.applicationService.deleteById(id);
	}

	@DeleteMapping("/deleteAll")
	public DataResult<List<GetAllApplicationResponse>> deleteAll() {
		return this.applicationService.deleteAll();
	}

	@PutMapping("/update")
	public DataResult<UpdateApplicationResponse> update(
			@RequestBody UpdateApplicationRequest updateApplicationRequest) {
		return this.applicationService.update(updateApplicationRequest);
	}

}
