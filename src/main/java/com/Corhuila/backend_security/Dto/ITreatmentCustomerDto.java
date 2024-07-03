package com.Corhuila.backend_security.Dto;

public interface ITreatmentCustomerDto extends IBasicDto {
  
  Double getPayment();

  Double getBalance();

  Long getTreatmentId();

  String getTreatment();

  Long getCustomerId();

  String getCustomer();

  Long getStateId();

  String getStateName();

}
