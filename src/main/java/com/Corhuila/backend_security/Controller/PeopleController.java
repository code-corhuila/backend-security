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
import com.Corhuila.backend_security.Entity.People;
import com.Corhuila.backend_security.IService.IPeopleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
/* #endregion */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/parameter/People")
public class PeopleController {

    @Autowired
    private IPeopleService service;

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

    @Operation(summary = "Obtener todas las personas", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de personas obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron personas")
    })

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<People>>> all() {
        try {
            return ResponseEntity.ok(new ApiResponseDto<List<People>>("Datos obtenidos", service.all(), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<List<People>>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Obtener una persona por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Persona encontrado"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrado")
    })

    @GetMapping("{id}")
	public ResponseEntity<ApiResponseDto<People>> show(@PathVariable Long id) {
		try {
			People people = service.findById(id);

			return ResponseEntity.ok(new ApiResponseDto<People>("Persona encontrado", people, true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(new ApiResponseDto<People>(e.getMessage(), null, false));
		}
	}

    @Operation(summary = "Obtener una persona por document_number", responses = {
        @ApiResponse(responseCode = "200", description = "Persona encontrado"),
        @ApiResponse(responseCode = "404", description = "Persona no encontrado")
    })

    @GetMapping("/getByDocumentNumber/{documentNumber}")
    public ResponseEntity<ApiResponseDto<People>> getPeopleByDocumentNumber(@PathVariable String documentNumber) {
        try {
            People people = service.getPeopleByDocumentNumber(documentNumber);

            return ResponseEntity.ok(new ApiResponseDto<People>("Persona encontrada", people, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<People>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Crear una nueva persona", responses = {
            @ApiResponse(responseCode = "201", description = "Persona creado")
    })

    @PostMapping
    public ResponseEntity<ApiResponseDto<People>> save(@RequestBody People people) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<People>("Datos guardados", service.save(people), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<People>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Actualizar una persona existente", responses = {
            @ApiResponse(responseCode = "200", description = "Persona actualizado"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrado")
    })

    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<People>> update(@PathVariable Long id, @RequestBody People people) {
        try {
            service.update(id, people);
            return ResponseEntity.ok(new ApiResponseDto<People>("Datos actualizados", people, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<People>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Eliminar una persona existente", responses = {
            @ApiResponse(responseCode = "204", description = "Persona eliminado"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrado")
    })
    
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<People>> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok(new ApiResponseDto<People>("Datos eliminados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<People>(e.getMessage(), null, false));
        }
    }
}
