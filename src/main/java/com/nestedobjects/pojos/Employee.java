package com.nestedobjects.pojos;

import java.util.Date;
import java.util.List;

public class Employee {
	private int employeeNumber;
	private String lastName;
	private String firstName;
	private String extension;
	private List<String> email;
	private String reportsTo;
	private String jobTitle;
	private Office office;
	private Date hireDate;

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	public String getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Office getOffice() {
		return this.office;
	}

	public void setJobTitle(String title) {
		this.jobTitle = title;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
}
