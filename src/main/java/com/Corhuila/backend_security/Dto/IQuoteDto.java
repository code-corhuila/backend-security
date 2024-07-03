package com.Corhuila.backend_security.Dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface IQuoteDto extends IBasicDto {
  
	LocalDateTime getDate();
	
	String getDateTransform();

	Long getStateId();

	String getStateName();

	Long getEmployeeId();

	String getEmployee();
	
	String getCustomer();

	String getServices();
	
	String getName();
	
	Integer getCoste();
	
	String getNameTreatment();
	
	String getTimeTransform();
	
	String getPhoneEmployee();
	
	String getPhoneCustomer();
	
	Integer getCosteTreatment();
	
	Integer getTotalPayment();
	
	Integer getPendingValue();
	
	String getPaymentMethod();
	
	Long getHeadquarterId();

	String getHeadquarter();

	Long getTreatmentCustomerId();

	String getTreatmentCustomer();

	LocalTime getStartTime();

	LocalTime getEndTime();
}
