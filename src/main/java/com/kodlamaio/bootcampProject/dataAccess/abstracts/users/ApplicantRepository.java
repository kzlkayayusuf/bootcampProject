package com.kodlamaio.bootcampProject.dataAccess.abstracts.users;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kodlamaio.bootcampProject.entities.users.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {

	List<Applicant> findByFirstName(String name);

	Applicant findByNationalityIdentity(String nationalityIdentity);

	Applicant findById(int id);

	@Modifying
	@Query(value = "insert into Applicants (about,id) VALUES (:about,:id)", nativeQuery = true)
	@Transactional
	void beAnApplicant(@Param("about") String about, @Param("id") int id);

	@Modifying
	@Query(value = "delete from Applicants where id = :id", nativeQuery = true)
	@Transactional
	void removeAnApplicant(@Param("id") int id);

	@Modifying
	@Query(value = "delete from Applications where applicant_id = :id", nativeQuery = true)
	@Transactional
	void removeApplicantFromApplication(@Param("id") int id);

	@Modifying
	@Query(value = "delete from Blacklists where applicant_id = :id", nativeQuery = true)
	@Transactional
	void removeApplicantFromBlacklist(@Param("id") int id);
}
