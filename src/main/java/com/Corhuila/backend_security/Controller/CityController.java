package com.Corhuila.backend_security.Controller;

/* #region Import */
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Corhuila.backend_security.Dto.ApiResponseDto;
import com.Corhuila.backend_security.Dto.ICityDto;
import com.Corhuila.backend_security.Entity.City;
import com.Corhuila.backend_security.IService.ICityService;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/* #endregion */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/parameter/City")
public class CityController extends BaseModelController<City, ICityService>{

	public CityController(ICityService service) {
        super("City"); 
        this.service = service;
    }
	
	
	@Operation(summary = "Metodo para datatable", responses = {
			@ApiResponse(responseCode = "200", description = "Datatable obtenido"),
			@ApiResponse(responseCode = "404", description = "Datatable obtenido"),
	})

	@GetMapping("/datatable")
	public ResponseEntity<ApiResponseDto<Page<?>>> datatable(@RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size,
			@RequestParam(name = "column_order") String columnOrder,
			@RequestParam(name = "column_direction") String columnDirection,
			@RequestParam(name = "search", required = false) String search) {
		try {
			List<Order> orders = new ArrayList<>();

			orders.add(new Order(columnDirection == "asc" ? Direction.ASC : Direction.DESC, columnOrder));

			Page<?> data = service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search);

			return ResponseEntity.ok(new ApiResponseDto<Page<?>>("Datatable obtenido", data, true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Page<?>>(e.getMessage(), null, false));
		}
	}

	

	@GetMapping("getCityById/{id}")
	public ResponseEntity<ApiResponseDto<ICityDto>> getCityById(@PathVariable Long id) {
		try {

			return ResponseEntity
					.ok(new ApiResponseDto<ICityDto>("Ciudad encontrada", service.findByIdCustom(id), true));

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<ICityDto>(e.getMessage(), null, false));
		}
	}
	
	
	@GetMapping("GetByEstateId/{estateId}")
	public ResponseEntity<ApiResponseDto<List<ICityDto>>> getByEstateId(@PathVariable Long estateId) {
		try {

			return ResponseEntity.ok(new ApiResponseDto<List<ICityDto>>("Ciudad encontrada", service.getByEstateId(estateId), true));

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<ICityDto>>(e.getMessage(), null, false));
		}
	}
	
	@GetMapping("export")
	public ResponseEntity<?> export(@RequestParam(name = "type") String type) {
		try {
			HttpHeaders headers = new HttpHeaders();
			
			if (type.equals("pdf")) {
				headers.setContentType(MediaType.APPLICATION_PDF);
		        headers.setContentDispositionFormData("CityExport", "CityExport.pdf");
			} else {
				headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
		        var contentDisposition = ContentDisposition.builder("attachment").filename("CityExport" + ".xls").build();
		        headers.setContentDisposition(contentDisposition);
			}
			
			return ResponseEntity.ok().headers(headers).body(this.service.export(type));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
}