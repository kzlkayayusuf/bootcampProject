package com.kodlamaio.bootcampProject.api.bootcamps;

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

import com.kodlamaio.bootcampProject.business.abstracts.bootcamps.BootcampService;
import com.kodlamaio.bootcampProject.business.requests.bootcamps.CreateBootcampRequest;
import com.kodlamaio.bootcampProject.business.requests.bootcamps.UpdateBootcampRequest;
import com.kodlamaio.bootcampProject.business.responses.bootcamps.CreateBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamps.GetAllBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamps.GetBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamps.UpdateBootcampResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bootcamps")
public class BootcampsController {
	private BootcampService bootcampService;

	@GetMapping("/getall")
	public DataResult<List<GetAllBootcampResponse>> getAll() {
		return this.bootcampService.getAll();
	}

	@PostMapping("/add")
	public DataResult<CreateBootcampResponse> add(@Valid @RequestBody CreateBootcampRequest createBootcampRequest) {
		return this.bootcampService.add(createBootcampRequest);
	}

	@GetMapping("/getByName/{name}")
	public DataResult<GetBootcampResponse> getByName(@PathVariable String name) {
		return bootcampService.getByName(name);
	}

	@GetMapping("/getById/{id}")
	public DataResult<GetBootcampResponse> getById(@PathVariable int id) {
		return this.bootcampService.getById(id);
	}

	@DeleteMapping("/deleteById/{id}")
	public Result deleteById(@PathVariable int id) {
		return this.bootcampService.deleteById(id);
	}

	@DeleteMapping("/deleteAll")
	public DataResult<List<GetAllBootcampResponse>> deleteAll() {
		return this.bootcampService.deleteAll();
	}

	@PutMapping("/update")
	public DataResult<UpdateBootcampResponse> update(@Valid @RequestBody UpdateBootcampRequest updateBootcampRequest) {
		return this.bootcampService.update(updateBootcampRequest);
	}
}
