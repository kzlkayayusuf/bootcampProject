package com.kodlamaio.bootcampProject.business.constants;

public class Regexes {
	public static final String Email = "([A-Za-z0-9-_.]+@[A-Za-z0-9-_]+(?:\\.[A-Za-z0-9]+)+)";
	public static final String Password = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,16}$";
}
