package com.kodlamaio.bootcampProject.dataAccess.abstracts.applications;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.applications.Bootcamp;

public interface BootcampRepository extends JpaRepository<Bootcamp, Integer>{
	Bootcamp findByName(String name);
	Bootcamp findById(int id);
}
