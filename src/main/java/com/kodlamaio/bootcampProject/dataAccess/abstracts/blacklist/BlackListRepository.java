package com.kodlamaio.bootcampProject.dataAccess.abstracts.blacklist;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.bootcampProject.entities.blacklist.BlackList;

public interface BlackListRepository extends JpaRepository<BlackList, Integer> {
		BlackList findById(int id);
}
