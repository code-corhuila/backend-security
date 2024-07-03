package com.Corhuila.backend_security.Dto;

public interface IEmployeeDto extends IBasicDto {
  String getCode();

  Long getPeopleId();

  String getPeople();

  Long getPositionId();

  String getPosition();

  Long getCompanyId();

  String getCompany();
}
