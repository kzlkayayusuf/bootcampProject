package com.kodlamaio.bootcampProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.BootcampService;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.create.CreateBootcampRequest;
import com.kodlamaio.bootcampProject.business.requests.update.UpdateBootcampRequest;
import com.kodlamaio.bootcampProject.business.responses.create.CreateBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetAllBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.read.GetBootcampResponse;
import com.kodlamaio.bootcampProject.business.responses.update.UpdateBootcampResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.BootcampRepository;
import com.kodlamaio.bootcampProject.entities.Bootcamp;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BootcampManager implements BootcampService {

	private BootcampRepository bootcampRepository;
	private ModelMapperService modelMapperService;

	@Override
	public DataResult<List<GetAllBootcampResponse>> getAll() {
		List<Bootcamp> bootcamps = this.bootcampRepository.findAll();
		List<GetAllBootcampResponse> bootcampResponse = bootcamps.stream()
				.map(bootcamp -> this.modelMapperService.forResponse().map(bootcamp, GetAllBootcampResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllBootcampResponse>>(bootcampResponse);
	}

	@Override
	public DataResult<CreateBootcampResponse> add(CreateBootcampRequest createBootcampRequest) {
		checkIfBootcampExistsByName(createBootcampRequest.getName());
		Bootcamp bootcamp = this.modelMapperService.forRequest().map(createBootcampRequest, Bootcamp.class);
		this.bootcampRepository.save(bootcamp);

		CreateBootcampResponse bootcampResponse = this.modelMapperService.forResponse().map(bootcamp,
				CreateBootcampResponse.class);

		return new SuccessDataResult<CreateBootcampResponse>(bootcampResponse, Messages.BootcampCreated);
	}

	@Override
	public DataResult<GetBootcampResponse> getByName(String name) {
		checkIfBootcampNotExistsByName(name);
		Bootcamp bootcamp = this.bootcampRepository.findByName(name);
		GetBootcampResponse bootcampResponse = this.modelMapperService.forResponse().map(bootcamp,
				GetBootcampResponse.class);

		return new SuccessDataResult<GetBootcampResponse>(bootcampResponse);
	}

	@Override
	public DataResult<GetBootcampResponse> getById(int id) {
		checkIfBootcampNotExistsById(id);
		Bootcamp bootcamp = this.bootcampRepository.findById(id);
		GetBootcampResponse bootcampResponse = this.modelMapperService.forResponse().map(bootcamp,
				GetBootcampResponse.class);

		return new SuccessDataResult<GetBootcampResponse>(bootcampResponse);
	}

	@Override
	public Result deleteById(int id) {
		checkIfBootcampNotExistsById(id);
		this.bootcampRepository.deleteById(id);
		return new SuccessResult(Messages.BootcampDeleted);
	}

	@Override
	public DataResult<List<GetAllBootcampResponse>> deleteAll() {
		List<Bootcamp> bootcamps = this.bootcampRepository.findAll();
		List<GetAllBootcampResponse> bootcampResponse = bootcamps.stream()
				.map(bootcamp -> this.modelMapperService.forResponse().map(bootcamp, GetAllBootcampResponse.class))
				.collect(Collectors.toList());
		this.bootcampRepository.deleteAll();
		return new SuccessDataResult<List<GetAllBootcampResponse>>(bootcampResponse, Messages.AllBootcampDeleted);
	}

	@Override
	public DataResult<UpdateBootcampResponse> update(UpdateBootcampRequest updateBootcampRequest) {
		checkIfBootcampNotExistsById(updateBootcampRequest.getId());
		Bootcamp bootcamp = this.modelMapperService.forRequest().map(updateBootcampRequest, Bootcamp.class);
		this.bootcampRepository.save(bootcamp);

		UpdateBootcampResponse bootcampResponse = this.modelMapperService.forResponse().map(bootcamp,
				UpdateBootcampResponse.class);

		return new SuccessDataResult<UpdateBootcampResponse>(bootcampResponse, Messages.BootcampUpdated);
	}

	public void checkIfBootcampExistsByName(String name) {
		Bootcamp bootcamp = this.bootcampRepository.findByName(name);
		if (bootcamp != null) {
			throw new BusinessException(Messages.NameExists);
		}
	}

	public void checkIfBootcampNotExistsById(int id) {
		Bootcamp bootcamp = this.bootcampRepository.findById(id);
		if (bootcamp == null) {
			throw new BusinessException(Messages.IdNotExists);
		}
	}

	public void checkIfBootcampNotExistsByName(String name) {
		Bootcamp bootcamp = this.bootcampRepository.findByName(name);
		if (bootcamp == null) {
			throw new BusinessException(Messages.NameNotExists);
		}
	}

}
