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

import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampProject.business.requests.create.CreateApplicantRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateApplicantRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.delete.DeleteApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllApplicantsResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateApplicantResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/applicants")
public class ApplicantsController {
	private ApplicantService applicantService;
	
	@GetMapping("/getall")
    public List<GetAllApplicantsResponse> getAll() {
        return applicantService.getAll();
    }

    @PostMapping("/add")
    public CreateApplicantResponse add(@RequestBody() CreateApplicantRequest createApplicantRequest) throws Exception {
        return this.applicantService.add(createApplicantRequest);
    }

    @GetMapping("/getByName/{name}")
    public GetApplicantResponse getByName(@RequestParam String name) {
        return applicantService.getByName(name);
    }

    @GetMapping("/getById/{id}")
    public GetApplicantResponse getById(@RequestParam int id) {
        return applicantService.getById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public DeleteApplicantResponse deleteById(@RequestParam int id) {
        return applicantService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public List<GetAllApplicantsResponse> deleteAll() {
       return applicantService.deleteAll();
    }

    @PutMapping("/update")
    public UpdateApplicantResponse update(@RequestBody() UpdateApplicantRequest updateApplicantRequest) {
        return applicantService.update(updateApplicantRequest);
    }
}
