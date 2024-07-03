package com.Corhuila.backend_security.Controller;

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
import com.Corhuila.backend_security.Dto.ModuleDto;
import com.Corhuila.backend_security.Entity.Module;
import com.Corhuila.backend_security.IService.IModuleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/parameter/Module")
public class ModuleController {

    @Autowired
    private IModuleService service;

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

    @Operation(summary = "Obtener todas las modulos", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de modulos obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron modulos")
    })

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Module>>> all() {
        try {
            return ResponseEntity.ok(new ApiResponseDto<List<Module>>("Datos obtenidos", service.all(), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<List<Module>>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Obtener una modulo por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Modulo encontrado"),
            @ApiResponse(responseCode = "404", description = "Modulo no encontrado")
    })

    @GetMapping("{id}")
	public ResponseEntity<ApiResponseDto<ModuleDto>> show(@PathVariable Long id) {
		try {
			ModuleDto module = service.findById(id);

			return ResponseEntity.ok(new ApiResponseDto<ModuleDto>("Modulo encontrado", module, true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(new ApiResponseDto<ModuleDto>(e.getMessage(), null, false));
		}
	}

    @Operation(summary = "Crear una nueva modulo", responses = {
            @ApiResponse(responseCode = "201", description = "Modulo creado")
    })

    @PostMapping
    public ResponseEntity<ApiResponseDto<Module>> save(@RequestBody ModuleDto module) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<Module>("Datos guardados", service.save(module), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Module>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Actualizar una modulo existente", responses = {
            @ApiResponse(responseCode = "200", description = "Modulo actualizado"),
            @ApiResponse(responseCode = "404", description = "Modulo no encontrado")
    })

    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<Module>> update(@PathVariable Long id, @RequestBody ModuleDto module) {
        try {
            service.update(id, module);
            return ResponseEntity.ok(new ApiResponseDto<Module>("Datos actualizados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Module>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Eliminar una modulo existente", responses = {
            @ApiResponse(responseCode = "204", description = "Modulo eliminado"),
            @ApiResponse(responseCode = "404", description = "Modulo no encontrado")
    })
    
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<Module>> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok(new ApiResponseDto<Module>("Datos eliminados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Module>(e.getMessage(), null, false));
        }
    }
}
