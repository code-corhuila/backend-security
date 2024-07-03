package com.Corhuila.backend_security.Dto;

public interface ITreatmentServiceDto extends IBasicDto {
	
	
	Long getTreatmentId();

	String getTreatment();

	Long getServiceId();

	String getService();
}
