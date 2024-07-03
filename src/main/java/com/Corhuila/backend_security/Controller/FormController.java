package com.Corhuila.backend_security.Controller;

/* #region Import */
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Corhuila.backend_security.Dto.ApiResponseDto;
import com.Corhuila.backend_security.Dto.FormDto;
import com.Corhuila.backend_security.Entity.Form;
import com.Corhuila.backend_security.IService.IFormService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
/* #endregion */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/parameter/Form")
public class FormController {

    @Autowired
    private IFormService service;

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

    @Operation(summary = "Obtener todas las formularios", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de formularios obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron formularios")
    })

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Form>>> all() {
        try {
            return ResponseEntity.ok(new ApiResponseDto<List<Form>>("Datos obtenidos", service.all(), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<List<Form>>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Obtener una formulario por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Formulario encontrado"),
            @ApiResponse(responseCode = "404", description = "Formulario no encontrado")
    })

    @GetMapping("{id}")
	public ResponseEntity<ApiResponseDto<FormDto>> show(@PathVariable Long id) {
		try {
			FormDto form = service.findById(id);

			return ResponseEntity.ok(new ApiResponseDto<FormDto>("Tipo de sangre encontrado", form, true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(new ApiResponseDto<FormDto>(e.getMessage(), null, false));
		}
	}

    @Operation(summary = "Crear una nueva formulario", responses = {
            @ApiResponse(responseCode = "201", description = "Formulario creada")
    })

    @PostMapping
    public ResponseEntity<ApiResponseDto<Form>> save(@RequestBody FormDto form) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<Form>("Datos guardados", service.save(form), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Form>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Actualizar una formulario existente", responses = {
            @ApiResponse(responseCode = "200", description = "Formulario actualizado"),
            @ApiResponse(responseCode = "404", description = "Formulario no encontrado")
    })

    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<Form>> update(@PathVariable Long id, @RequestBody FormDto form) {
        try {
            service.update(id, form);
            return ResponseEntity.ok(new ApiResponseDto<Form>("Datos actualizados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Form>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Eliminar una formulario existente", responses = {
            @ApiResponse(responseCode = "204", description = "Formulario eliminado"),
            @ApiResponse(responseCode = "404", description = "Formulario no encontrado")
    })
    
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<Form>> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok(new ApiResponseDto<Form>("Datos eliminados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Form>(e.getMessage(), null, false));
        }
    }
}
