package com.Corhuila.backend_security.Dto;

public interface IQuoteServiceDto extends IBasicDto {

  Long getQuoteId();
  
  String getQuote();
  
  Long getServiceId();
  
  String getService();
  
  Long getStateId();
  
  String getStateName();
  
}
