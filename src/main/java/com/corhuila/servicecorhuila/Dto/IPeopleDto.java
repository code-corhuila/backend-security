package com.corhuila.servicecorhuila.Dto;

import java.sql.Date;

public interface IPeopleDto extends IBasicDto {
  String getDocumentNumber();
  String getSurname();
  String getAddress();  
  String getPhone();
  String getMail();
  Date getBirthDate();
  Boolean getGender();
  String getAttendantName();
  String getAttendingPhone();

  Long getDocumentTypeId();
  String getDocumentType();
  Long getUserId();
  String getUser();
  Long getStateId();
  String getStateName();
  Long getCityId();
  String getCity();
}
