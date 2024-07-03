package com.Corhuila.backend_security.Controller;
//package com.sgsst.sgsstbackend.Controller;
//
///* #region Import */
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.domain.Sort.Order;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sgsst.sgsstbackend.Dto.ApiResponseDto;
//import com.sgsst.sgsstbackend.Entity.User;
//import com.sgsst.sgsstbackend.IService.IUserService;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
///* #endregion */
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("api/parameter/User")
//public class UserController {
//
//    @Autowired
//    private IUserService service;
//
//    @Operation(summary = "Metodo para datatable", responses = {
//            @ApiResponse(responseCode = "200", description = "Datatable obtenido"),
//            @ApiResponse(responseCode = "404", description = "Datatable obtenido"),
//    })
//
//    @GetMapping("/datatable")
//    public ResponseEntity<ApiResponseDto<Page<?>>> datatable(@RequestParam(name = "page") Integer page,
//            @RequestParam(name = "size") Integer size,
//            @RequestParam(name = "column_order") String columnOrder,
//            @RequestParam(name = "column_direction") String columnDirection,
//            @RequestParam(name = "search", required = false) String search) {
//        try {
//            List<Order> orders = new ArrayList<>();
//
//            orders.add(new Order(columnDirection == "asc" ? Direction.ASC : Direction.DESC, columnOrder));
//
//            return ResponseEntity.ok(new ApiResponseDto<Page<?>>("Datos obtenidos",
//                    service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search), true));
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body(new ApiResponseDto<Page<?>>(e.getMessage(), null, false));
//        }
//    }
//
//    @Operation(summary = "Obtener todas las usuarios", responses = {
//            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida"),
//            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios")
//    })
//
//    @GetMapping
//    public ResponseEntity<ApiResponseDto<List<User>>> all() {
//        try {
//            return ResponseEntity.ok(new ApiResponseDto<List<User>>("Datos obtenidos", service.all(), true));
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body(new ApiResponseDto<List<User>>(e.getMessage(), null, false));
//        }
//    }
//
//    @Operation(summary = "Obtener una usuario por ID", responses = {
//            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
//            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
//    })
//
//    @GetMapping("{id}")
//	public ResponseEntity<ApiResponseDto<User>> show(@PathVariable Long id) {
//		try {
//			User user = service.findById(id);
//
//			return ResponseEntity.ok(new ApiResponseDto<User>("Usuario encontrado", user, true));
//		} catch (Exception e) {
//			return ResponseEntity.internalServerError()
//					.body(new ApiResponseDto<User>(e.getMessage(), null, false));
//		}
//	}
//
//    @Operation(summary = "Crear una nueva usuario", responses = {
//            @ApiResponse(responseCode = "201", description = "Usuario creado")
//    })
//
//    @PostMapping
//    public ResponseEntity<ApiResponseDto<User>> save(@RequestBody User user) {
//        try {
//            return ResponseEntity.ok(new ApiResponseDto<User>("Datos guardados", service.save(user), true));
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body(new ApiResponseDto<User>(e.getMessage(), null, false));
//        }
//    }
//
//    @Operation(summary = "Actualizar una usuario existente", responses = {
//            @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
//            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
//    })
//
//    @PutMapping("{id}")
//    public ResponseEntity<ApiResponseDto<User>> update(@PathVariable Long id, @RequestBody User user) {
//        try {
//            service.update(id, user);
//            return ResponseEntity.ok(new ApiResponseDto<User>("Datos actualizados", null, true));
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body(new ApiResponseDto<User>(e.getMessage(), null, false));
//        }
//    }
//
//    @Operation(summary = "Eliminar una usuario existente", responses = {
//            @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
//            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
//    })
//    
//    @DeleteMapping("{id}")
//    public ResponseEntity<ApiResponseDto<User>> delete(@PathVariable Long id) {
//        try {
//            service.delete(id);
//            return ResponseEntity.ok(new ApiResponseDto<User>("Datos eliminados", null, true));
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body(new ApiResponseDto<User>(e.getMessage(), null, false));
//        }
//    }
//}
