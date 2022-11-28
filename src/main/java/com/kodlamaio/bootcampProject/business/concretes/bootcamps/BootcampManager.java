package com.kodlamaio.bootcampProject.business.concretes.bootcamps;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.bootcamps.BootcampService;
import com.kodlamaio.bootcampProject.business.abstracts.users.InstructorService;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.bootcamps.CreateBootcampRequest;
import com.kodlamaio.bootcampProject.business.requests.bootcamps.UpdateBootcampRequest;
import com.kodlamaio.bootcampProject.business.responses.bootcamps.CreateBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamps.GetAllBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamps.GetBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.bootcamps.UpdateBootcampResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.bootcamps.BootcampRepository;
import com.kodlamaio.bootcampProject.entities.bootcamps.Bootcamp;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BootcampManager implements BootcampService {

	private BootcampRepository bootcampRepository;
	private InstructorService instructorService;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllBootcampResponse>> getAll() {
		List<Bootcamp> bootcamps = this.bootcampRepository.findAll();
		List<GetAllBootcampResponse> bootcampResponse = bootcamps.stream()
				.map(bootcamp -> this.modelMapperService.forResponse().map(bootcamp, GetAllBootcampResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllBootcampResponse>>(bootcampResponse, Messages.Bootcamp.ListAll);
	}

	@Override
	public DataResult<CreateBootcampResponse> add(CreateBootcampRequest createBootcampRequest) {
		checkIfBootcampExistsByName(createBootcampRequest.getName());
		instructorService.checkIfInstructorExistById(createBootcampRequest.getInstructorId());
		checkIfStartDateBiggerThanEndDate(createBootcampRequest.getStartDate(), createBootcampRequest.getEndDate());
		Bootcamp bootcamp = this.modelMapperService.forRequest().map(createBootcampRequest, Bootcamp.class);
		bootcamp.setId(0); // postreSql id de yazılan identity i görmediği için bootcampId yi Instructor
							// idsi ile eşliyor bunun önüne geçmek için bootcampIdyi burada set etmek
							// gerekiyor.
		this.bootcampRepository.save(bootcamp);

		CreateBootcampResponse bootcampResponse = this.modelMapperService.forResponse().map(bootcamp,
				CreateBootcampResponse.class);

		return new SuccessDataResult<CreateBootcampResponse>(bootcampResponse, Messages.Bootcamp.Created);
	}

	@Override
	public DataResult<GetBootcampResponse> getByName(String name) {
		checkIfBootcampNotExistsByName(name);
		Bootcamp bootcamp = this.bootcampRepository.findByName(name);
		GetBootcampResponse bootcampResponse = this.modelMapperService.forResponse().map(bootcamp,
				GetBootcampResponse.class);

		return new SuccessDataResult<GetBootcampResponse>(bootcampResponse, Messages.Bootcamp.ListByName);
	}

	@Override
	public DataResult<GetBootcampResponse> getById(int id) {
		checkIfBootcampNotExistsById(id);
		Bootcamp bootcamp = this.bootcampRepository.findById(id);
		GetBootcampResponse bootcampResponse = this.modelMapperService.forResponse().map(bootcamp,
				GetBootcampResponse.class);

		return new SuccessDataResult<GetBootcampResponse>(bootcampResponse, Messages.Bootcamp.ListById);
	}

	@Override
	public Result deleteById(int id) {
		checkIfBootcampNotExistsById(id);
		this.bootcampRepository.deleteById(id);
		return new SuccessResult(Messages.Bootcamp.Deleted);
	}

	@Override
	public DataResult<List<GetAllBootcampResponse>> deleteAll() {
		List<Bootcamp> bootcamps = this.bootcampRepository.findAll();
		List<GetAllBootcampResponse> bootcampResponse = bootcamps.stream()
				.map(bootcamp -> this.modelMapperService.forResponse().map(bootcamp, GetAllBootcampResponse.class))
				.collect(Collectors.toList());
		this.bootcampRepository.deleteAll();
		return new SuccessDataResult<List<GetAllBootcampResponse>>(bootcampResponse, Messages.Bootcamp.AllDeleted);
	}

	@Override
	public DataResult<UpdateBootcampResponse> update(UpdateBootcampRequest updateBootcampRequest) {
		checkIfBootcampNotExistsById(updateBootcampRequest.getId());
		Bootcamp bootcamp = this.modelMapperService.forRequest().map(updateBootcampRequest, Bootcamp.class);
		this.bootcampRepository.save(bootcamp);

		UpdateBootcampResponse bootcampResponse = this.modelMapperService.forResponse().map(bootcamp,
				UpdateBootcampResponse.class);

		return new SuccessDataResult<UpdateBootcampResponse>(bootcampResponse, Messages.Bootcamp.Updated);
	}

	@Override
	public void checkIfBootcampIsActive(int id) {
		checkIfBootcampNotExistsById(id);
		Bootcamp bootcamp = bootcampRepository.findById(id);
		if (bootcamp.getState() == 2) {
			throw new BusinessException(Messages.Bootcamp.IsNotActive);
		}

	}

	@Override
	public void checkIfBootcampExistById(int id) {
		if (!bootcampRepository.existsById(id)) {
			throw new BusinessException(Messages.Bootcamp.NotExists);
		}

	}

	public void checkIfBootcampExistsByName(String name) {
		Bootcamp bootcamp = this.bootcampRepository.findByName(name);
		if (bootcamp != null) {
			throw new BusinessException(Messages.Bootcamp.NameExists);
		}
	}

	public void checkIfBootcampNotExistsById(int id) {
		Bootcamp bootcamp = this.bootcampRepository.findById(id);
		if (bootcamp == null) {
			throw new BusinessException(Messages.Bootcamp.NotExists);
		}
	}

	public void checkIfBootcampNotExistsByName(String name) {
		Bootcamp bootcamp = this.bootcampRepository.findByName(name);
		if (bootcamp == null) {
			throw new BusinessException(Messages.Bootcamp.NameNotExists);
		}
	}

	private void checkIfStartDateBiggerThanEndDate(LocalDate startDate, LocalDate endDate) {
		if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
			throw new BusinessException(Messages.Bootcamp.StartDateBigThanEndDate);
		}
	}

}
