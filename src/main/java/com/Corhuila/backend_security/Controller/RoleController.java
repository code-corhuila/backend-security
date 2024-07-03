package com.Corhuila.backend_security.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Corhuila.backend_security.Dto.ApiResponseDto;
import com.Corhuila.backend_security.Dto.IRoleDto;
import com.Corhuila.backend_security.IService.IRoleService;
import com.Corhuila.backend_security.Security.Entity.Role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/parameter/Role")
public class RoleController extends BaseModelController<Role, IRoleService> {

    public RoleController(IRoleService service) {
        super("Role"); 
        this.service = service;
    }
    
	@Operation(summary = "Metodo para datatable", responses = {
			@ApiResponse(responseCode = "200", description = "Datatable obtenido"),
			@ApiResponse(responseCode = "404", description = "Datatable obtenido"),
	})

	@GetMapping("/datatable")
	public ResponseEntity<ApiResponseDto<Page<IRoleDto>>> datatable(@RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size,
			@RequestParam(name = "column_order") String columnOrder,
			@RequestParam(name = "column_direction") String columnDirection,
			@RequestParam(name = "search", required = false) String search) {
		try {

			List<Order> orders = new ArrayList<>();

			orders.add(new Order(columnDirection == "asc" ? Direction.ASC : Direction.DESC, columnOrder));

			Page<IRoleDto> data = service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search);

			return ResponseEntity.ok(new ApiResponseDto<Page<IRoleDto>>("Datos obtenidos", data, true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(new ApiResponseDto<Page<IRoleDto>>(e.getMessage(), null, false));
		}
	}	
}
