package com.Corhuila.backend_security.Controller;

/* #region Import */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Corhuila.backend_security.Dto.ApiResponseDto;
import com.Corhuila.backend_security.Entity.FormModule;
import com.Corhuila.backend_security.IService.IFormModuleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
/* #endregion */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/parameter/FormModule")
public class FormModuleController {

    @Autowired
    private IFormModuleService service;

    @Operation(summary = "Obtener todas las modulo formularios", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de modulo formularios obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron modulo formularios")
    })

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<FormModule>>> all() {
        try {
            return ResponseEntity.ok(new ApiResponseDto<List<FormModule>>("Datos obtenidos", service.all(), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<List<FormModule>>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Obtener una modulo formulario por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Modulo Formulario encontrado"),
            @ApiResponse(responseCode = "404", description = "Modulo Formulario no encontrado")
    })

    @GetMapping("{id}")
	public ResponseEntity<ApiResponseDto<FormModule>> show(@PathVariable Long id) {
		try {
			FormModule formModule = service.findById(id);

			return ResponseEntity.ok(new ApiResponseDto<FormModule>("Modulo Formulario encontrado", formModule, true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(new ApiResponseDto<FormModule>(e.getMessage(), null, false));
		}
	}

    @Operation(summary = "Crear una nueva modulo formulario", responses = {
            @ApiResponse(responseCode = "201", description = "Modulo Formulario creado")
    })

    @PostMapping
    public ResponseEntity<ApiResponseDto<FormModule>> save(@RequestBody FormModule formModule) {
        try {
            return ResponseEntity.ok(new ApiResponseDto<FormModule>("Datos guardados", service.save(formModule), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<FormModule>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Actualizar una modulo formulario existente", responses = {
            @ApiResponse(responseCode = "200", description = "Modulo Formulario actualizado"),
            @ApiResponse(responseCode = "404", description = "Modulo Formulario no encontrado")
    })

    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<FormModule>> update(@PathVariable Long id, @RequestBody FormModule formModule) {
        try {
            service.update(id, formModule);
            return ResponseEntity.ok(new ApiResponseDto<FormModule>("Datos actualizados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<FormModule>(e.getMessage(), null, false));
        }
    }

    @Operation(summary = "Eliminar una modulo formulario existente", responses = {
            @ApiResponse(responseCode = "204", description = "Modulo Formulario eliminado"),
            @ApiResponse(responseCode = "404", description = "Modulo Formulario no encontrado")
    })
    
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<FormModule>> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok(new ApiResponseDto<FormModule>("Datos eliminados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<FormModule>(e.getMessage(), null, false));
        }
    }
}
