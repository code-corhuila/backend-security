package com.Corhuila.backend_security.Dto;

import java.sql.Date;

public interface ITreatmentCustomerPaymentDto extends IBasicDto {
  Double getPayment();

  Date getDate();

  Long getTreatmentCustomerId();

  String getTreatmentCustomer();

  Long getpaymentMethodId();

  String getpaymentMethod();
  
  String getCustomerId();
  
}