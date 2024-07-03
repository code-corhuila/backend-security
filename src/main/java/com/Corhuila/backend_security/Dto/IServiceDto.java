package com.Corhuila.backend_security.Dto;

import java.math.BigDecimal;

public interface IServiceDto extends IBasicDto {
	
	String getCode();

	BigDecimal getPrice();

	Long getServiceTypeId();
	
	String getServiceType();
	
	Long getCompanyId();
	
	String getCompany();
}
