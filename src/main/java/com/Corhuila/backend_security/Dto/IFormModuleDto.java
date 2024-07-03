package com.Corhuila.backend_security.Dto;

public interface IFormModuleDto extends IBasicDto {
  Long getFormId();

  String getForm();

  Long getModuleId();

  String getModule();
}
