package com.Corhuila.backend_security.Dto;

public interface ICityDto extends IBasicDto {
    Long getEstateId();
    String getEstate();
    Long getCountryId();
    String getCountry();
}
