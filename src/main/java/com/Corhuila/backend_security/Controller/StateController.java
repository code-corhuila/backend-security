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
import com.Corhuila.backend_security.Dto.IStateDto;
import com.Corhuila.backend_security.Entity.State;
import com.Corhuila.backend_security.IService.IStateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
/* #endregion */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/parameter/State")
public class StateController extends BaseModelController<State, IStateService> {

	public StateController(IStateService service) {
        super("State"); 
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

            return ResponseEntity.ok(new ApiResponseDto<Page<?>>("Datos obtenidos",
                    service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Page<?>>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Obtener todas las estados", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de estados obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron estados")
    })

    @GetMapping("GetByStateId/{stateTypeId}")
	public ResponseEntity<ApiResponseDto<List<IStateDto>>> GetByStateId(@PathVariable Long stateTypeId) {
		try {
			return ResponseEntity.ok(new ApiResponseDto<List<IStateDto>>("Estado encontrado", this.service.getByStateId(stateTypeId), true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<IStateDto>>(e.getMessage(), null, false));
		}
	}
    
    @GetMapping("export")
	public ResponseEntity<?> export(@RequestParam(name = "type") String type) {
		try {
			HttpHeaders headers = new HttpHeaders();
			
			if (type.equals("pdf")) {
				headers.setContentType(MediaType.APPLICATION_PDF);
		        headers.setContentDispositionFormData("StateExport", "StateExport.pdf");
			} else {
				headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
		        var contentDisposition = ContentDisposition.builder("attachment").filename("StateExport.xls").build();
		        headers.setContentDisposition(contentDisposition);
			}
			
			return ResponseEntity.ok().headers(headers).body(this.service.export(type));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
}
