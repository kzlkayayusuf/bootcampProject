package com.kodlamaio.bootcampProject.dataAccess.abstracts.blacklists;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.blacklists.Blacklist;

public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {
	Blacklist findById(int id);
	boolean existsBlacklistByApplicantId(int id);
}
