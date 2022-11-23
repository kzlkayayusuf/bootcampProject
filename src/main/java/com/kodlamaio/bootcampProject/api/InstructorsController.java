package com.kodlamaio.bootcampProject.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.bootcampProject.business.abstracts.InstructorService;
import com.kodlamaio.bootcampProject.business.requests.create.CreateInstructorRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateInstructorRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.delete.DeleteInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllInstructorsResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetInstructorResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateInstructorResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/instructors")
public class InstructorsController {
	private InstructorService instructorService;
	
	@GetMapping("/getall")
    public List<GetAllInstructorsResponse> getAll() {
        return instructorService.getAll();
    }

    @PostMapping("/add")
    public CreateInstructorResponse add(@RequestBody() CreateInstructorRequest createInstructorRequest) {
        return this.instructorService.add(createInstructorRequest);
    }

    @GetMapping("/getByName/{name}")
    public GetInstructorResponse getByName(@RequestParam String name) {
        return instructorService.getByName(name);
    }

    @GetMapping("/getById/{id}")
    public GetInstructorResponse getById(@RequestParam int id) {
        return instructorService.getById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public DeleteInstructorResponse deleteById(@RequestParam int id) {
        return instructorService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public List<GetAllInstructorsResponse> deleteAll() {
       return instructorService.deleteAll();
    }

    @PutMapping("/update")
    public UpdateInstructorResponse update(@RequestBody() UpdateInstructorRequest updateInstructorRequest) {
        return instructorService.update(updateInstructorRequest);
    }
}
