package com.corhuila.servicecorhuila.Dto;

public interface ICityDto extends IBasicDto {
    Long getEstateId();
    String getEstate();
    Long getCountryId();
    String getCountry();
}
