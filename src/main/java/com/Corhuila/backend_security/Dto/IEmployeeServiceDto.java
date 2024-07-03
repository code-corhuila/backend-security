package com.Corhuila.backend_security.Dto;

public interface IEmployeeServiceDto extends IBasicDto {
  Long getServiceId();

  String getService();

  Long getEmployeeId();

  String getEmployee();
}
