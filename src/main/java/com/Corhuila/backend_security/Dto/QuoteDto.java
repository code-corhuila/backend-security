package com.Corhuila.backend_security.Dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class QuoteDto {

	private Long id;
	
	private String code;
	
	private LocalDateTime date;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private Long stateId;

	private String stateName;
	
	private Long employeeId;

	private String employee;

	private Long headquarterId;

	private String headquarter;

	private Long treatmentCustomerId;

	private String treatmentCustomer;

	private Boolean state;

	private List<Long> serviceIds;

	public Long getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public Long getHeadquarterId() {
		return headquarterId;
	}

	public void setHeadquarterId(Long headquarterId) {
		this.headquarterId = headquarterId;
	}

	public String getHeadquarter() {
		return headquarter;
	}

	public void setHeadquarter(String headquarter) {
		this.headquarter = headquarter;
	}

	public Long getTreatmentCustomerId() {
		return treatmentCustomerId;
	}

	public void setTreatmentCustomerId(Long treatmentCustomerId) {
		this.treatmentCustomerId = treatmentCustomerId;
	}

	public String getTreatmentCustomer() {
		return treatmentCustomer;
	}

	public void setTreatmentCustomer(String treatmentCustomer) {
		this.treatmentCustomer = treatmentCustomer;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public List<Long> getServiceIds() {
		return serviceIds;
	}

	public void setServiceIds(List<Long> serviceIds) {
		this.serviceIds = serviceIds;
	}	
}
