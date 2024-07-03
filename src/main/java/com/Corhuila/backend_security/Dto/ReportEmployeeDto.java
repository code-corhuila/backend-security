package com.Corhuila.backend_security.Dto;

import java.time.LocalDate;

public class ReportEmployeeDto {

	private String fullName;
	private String documentType;
	private String documentNumber;
	private String documentIssuanceCity;
	private LocalDate birthDate;
	private String age;
	private String gender;
	private String scholarship;
	private String civilStatus;
	private String bloodType;
	private String positions;
	private String city;
	private String address;
	private String attendantName;
	private String attendingPhone;
	
	public ReportEmployeeDto() {}
	
	public ReportEmployeeDto(String fullName, String documentType, String documentNumber, String documentIssuanceCity,
			LocalDate birthDate, String age, String gender, String scholarship, String civilStatus, String bloodType,
			String positions, String city, String address, String attendantName, String attendingPhone) {
		super();
		this.fullName = fullName;
		this.documentType = documentType;
		this.documentNumber = documentNumber;
		this.documentIssuanceCity = documentIssuanceCity;
		this.birthDate = birthDate;
		this.age = age;
		this.gender = gender;
		this.scholarship = scholarship;
		this.civilStatus = civilStatus;
		this.bloodType = bloodType;
		this.positions = positions;
		this.city = city;
		this.address = address;
		this.attendantName = attendantName;
		this.attendingPhone = attendingPhone;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getDocumentIssuanceCity() {
		return documentIssuanceCity;
	}

	public void setDocumentIssuanceCity(String documentIssuanceCity) {
		this.documentIssuanceCity = documentIssuanceCity;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getScholarship() {
		return scholarship;
	}

	public void setScholarship(String scholarship) {
		this.scholarship = scholarship;
	}

	public String getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAttendantName() {
		return attendantName;
	}

	public void setAttendantName(String attendantName) {
		this.attendantName = attendantName;
	}

	public String getAttendingPhone() {
		return attendingPhone;
	}

	public void setAttendingPhone(String attendingPhone) {
		this.attendingPhone = attendingPhone;
	}
	
}
