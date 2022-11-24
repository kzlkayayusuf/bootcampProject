package com.kodlamaio.bootcampProject.api.users;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampProject.business.requests.create.CreateApplicantRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateApplicantRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateApplicantResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/applicants")
public class ApplicantsController {
	private ApplicantService applicantService;

	@GetMapping("/getall")
	public DataResult<List<GetAllApplicantResponse>> getAll() {
		return this.applicantService.getAll();
	}

	@PostMapping("/add")
	public DataResult<CreateApplicantResponse> add(@RequestBody CreateApplicantRequest createApplicantRequest) {
		return this.applicantService.add(createApplicantRequest);
	}

	@GetMapping("/getByName/{name}")
	public DataResult<GetApplicantResponse> getByName(@PathVariable String name) {
		return applicantService.getByName(name);
	}

	@GetMapping("/getById/{id}")
	public DataResult<GetApplicantResponse> getById(@PathVariable int id) {
		return this.applicantService.getById(id);
	}

	@DeleteMapping("/deleteById/{id}")
	public Result deleteById(@PathVariable int id) {
		return applicantService.deleteById(id);
	}

	@DeleteMapping("/deleteAll")
	public DataResult<List<GetAllApplicantResponse>> deleteAll() {
		return applicantService.deleteAll();
	}

	@PutMapping("/update")
	public DataResult<UpdateApplicantResponse> update(@RequestBody UpdateApplicantRequest updateApplicantRequest) {
		return this.applicantService.update(updateApplicantRequest);
	}

}
