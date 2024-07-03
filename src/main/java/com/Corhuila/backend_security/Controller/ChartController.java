package com.Corhuila.backend_security.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Corhuila.backend_security.Dto.ApiResponseDto;
import com.Corhuila.backend_security.Dto.IChartLossDateContract;
import com.Corhuila.backend_security.Dto.IChartPeopleStateDto;
import com.Corhuila.backend_security.IService.IChartService;

@RestController
@RequestMapping("api/charts")
public class ChartController {
	
	@Autowired
	private IChartService _service;
	
	@GetMapping("people-state")
	public ResponseEntity<ApiResponseDto<List<IChartPeopleStateDto>>> getPeopleState() {
		try {
			return ResponseEntity.ok(new ApiResponseDto<List<IChartPeopleStateDto>>("Datos obtenidos", this._service.getChartPeopleState(), true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<IChartPeopleStateDto>>(e.getMessage(), null, false));
		}
	}
	
	@GetMapping("loss-date-contract")
	public ResponseEntity<ApiResponseDto<List<IChartLossDateContract>>> getChartLossDateContract() {
		try {
			return ResponseEntity.ok(new ApiResponseDto<List<IChartLossDateContract>>("Datos obtenidos", this._service.getChartLossDateContract(), true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<IChartLossDateContract>>(e.getMessage(), null, false));
		}
	}

}
