package com.kodlamaio.bootcampProject.api.users;

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

import com.kodlamaio.bootcampProject.business.abstracts.users.InstructorService;
import com.kodlamaio.bootcampProject.business.requests.users.instructors.CreateInstructorRequest;
import com.kodlamaio.bootcampProject.business.requests.users.instructors.UpdateInstructorRequest;
import com.kodlamaio.bootcampProject.business.responses.users.instructors.CreateInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.users.instructors.GetAllInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.users.instructors.GetInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.users.instructors.UpdateInstructorResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/instructors")
public class InstructorsController {
	private InstructorService instructorService;

	@GetMapping("/getall")
	public DataResult<List<GetAllInstructorResponse>> getAll() {
		return this.instructorService.getAll();
	}

	@PostMapping("/add")
	public DataResult<CreateInstructorResponse> add(
			@Valid @RequestBody CreateInstructorRequest createInstructorRequest) {
		return this.instructorService.add(createInstructorRequest);
	}

	@GetMapping("/getByName/{name}")
	public DataResult<List<GetAllInstructorResponse>> getByName(@PathVariable String name) {
		return instructorService.getByName(name);
	}

	@GetMapping("/getById/{id}")
	public DataResult<GetInstructorResponse> getById(@PathVariable int id) {
		return this.instructorService.getById(id);
	}

	@DeleteMapping("/deleteById/{id}")
	public Result deleteById(@PathVariable int id) {
		return instructorService.deleteById(id);
	}

	@DeleteMapping("/deleteAll")
	public DataResult<List<GetAllInstructorResponse>> deleteAll() {
		return instructorService.deleteAll();
	}

	@PutMapping("/update")
	public DataResult<UpdateInstructorResponse> update(
			@Valid @RequestBody UpdateInstructorRequest updateInstructorRequest) {
		return this.instructorService.update(updateInstructorRequest);
	}
}
