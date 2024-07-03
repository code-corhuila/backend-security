package com.Corhuila.backend_security.Dto;

public interface IFileDto extends IBasicDto {
  String getFileExtension();
  String getTable();
  String getTableId();
}
