package com.kodlamaio.bootcampProject.business.constants;

public class Messages {

	public static class Applicant {
		public static final String Created = "Applicant created.";
		public static final String Updated = "Applicant updated.";
		public static final String Deleted = "Applicant deleted.";
		public static final String AllDeleted = "All Applicant Deleted!";
		public static final String ListAll = "Applicants listed.";
		public static final String ListById = "Applicant listed by id.";
		public static final String ListByName = "Applicant listed by name.";
		public static final String AlreadyApplicant = "User is already an applicant.";
		public static final String BecameApplicant = "Employee became applicant.";
		public static final String Exists = "Applicant already exists.";
		public static final String NotExists = "Applicant not exists.";
		public static final String AboutValid = "About must be between 5 and 50 characters";
		public static final String NameNotExists = "That name not exists!";
	}

	public static class Employee {
		public static final String Created = "Employee created.";
		public static final String Updated = "Employee updated.";
		public static final String Deleted = "Employee deleted.";
		public static final String AllDeleted = "All Employee Deleted!";
		public static final String ListAll = "Employees listed.";
		public static final String ListById = "Employee listed by id.";
		public static final String ListByName = "Employee listed by name.";
		public static final String Exists = "Employee already exists.";
		public static final String NotExists = "Employee not exists.";
		public static final String NotAnEmployee = "User is not an employee.";
		public static final String NameNotExists = "That name not exists!";
	}

	public static class Instructor {
		public static final String Created = "Instructor created.";
		public static final String Updated = "Instructor updated.";
		public static final String Deleted = "Instructor deleted.";
		public static final String AllDeleted = "All Instructor Deleted!";
		public static final String ListAll = "Instructors listed.";
		public static final String ListByName = "Instructor listed by name.";
		public static final String ListById = "Instructor listed by id.";
		public static final String Exists = "Instructor already exists.";
		public static final String NotExists = "Instructor not exists.";
		public static final String NameNotExists = "That name not exists!";
	}

	public static class Application {
		public static final String Created = "Application created.";
		public static final String Updated = "Application updated.";
		public static final String Deleted = "Application deleted.";
		public static final String AllDeleted = "All Application deleted.";
		public static final String ListAll = "Applications listed.";
		public static final String ListById = "Application listed by id.";
		public static final String NotExists = "Application not exists.";
		public static final String UserHasApplication = "User has already applied to a bootcamp";
		public static final String IsNotActive = "Application is not active";
	}

	public static class Bootcamp {
		public static final String Created = "Bootcamp created.";
		public static final String Updated = "Bootcamp updated.";
		public static final String Deleted = "Bootcamp deleted.";
		public static final String AllDeleted = "All Bootcamp Deleted!";
		public static final String ListAll = "Bootcamps listed.";
		public static final String ListById = "Bootcamp listed by id.";
		public static final String ListByName = "Bootcamp listed by name.";
		public static final String NameExists = "That name already exists!";
		public static final String NameNotExists = "That name not exists!";
		public static final String NotExists = "Bootcamp not exists.";
		public static final String IsNotActive = "Bootcamp is not active";
		public static final String StartDateBigThanEndDate = "Start date must be before end date";
	}

	public static class Blacklist {
		public static final String Created = "Applicant added to blacklist.";
		public static final String Updated = "Blacklist updated.";
		public static final String Deleted = "Applicant removed from blacklist.";
		public static final String ListAll = "Blacklists listed.";
		public static final String ListById = "Blacklist listed by id.";
		public static final String ApplicantInBlacklist = "Applicant is in blacklist.";
		public static final String NotExists = "Blacklist not exist.";
		public static final String RemovedFromApplication = " Removed from application";
		public static final String Blank = "";
	}
}
