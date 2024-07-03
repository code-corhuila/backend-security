package com.Corhuila.backend_security.Dto;

public interface ICompanyDto extends IBasicDto {
  String getNit();
  String getAddress();
  String getPhone();
  String getMail();
}
