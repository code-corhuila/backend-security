package com.Corhuila.backend_security.IService;

import org.springframework.data.domain.Page;

import com.Corhuila.backend_security.Dto.ReportEmployeeDto;

public interface IReportSelectionService {

	Page<ReportEmployeeDto> getReportEmployee(Integer page, Integer cantidad, String search, Boolean gender, Long statusId, Long positionId);
}
