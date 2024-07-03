/*package com.Corhuila.backend_security.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Corhuila.backend_security.Dto.ApiResponseDto;
import com.Corhuila.backend_security.IService.IBaseGenericService;
import com.Corhuila.backend_security.Entity.BaseModel;

@CrossOrigin(origins = "*")
@RestController
public class BaseGenericController<T extends BaseModel, S extends IBaseGenericService<T, D>, D> extends BaseModelController<T, S> {

    
    
    public BaseGenericController(String entityName) {
		super(entityName);
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/datatable")
    public ResponseEntity<ApiResponseDto<Page<?>>> datatable(@RequestParam(name = "page") Integer page,
                                                              @RequestParam(name = "size") Integer size,
                                                              @RequestParam(name = "column_order") String columnOrder,
                                                              @RequestParam(name = "column_direction") String columnDirection,
                                                              @RequestParam(name = "search", required = false) String search) {
        try {
            List<Order> orders = new ArrayList<>();
            orders.add(new Order(columnDirection.equals("asc") ? Direction.ASC : Direction.DESC, columnOrder));
            Page<?> data = service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search);
            return ResponseEntity.ok(new ApiResponseDto<>("Datos obtenidos", data, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponseDto<>(e.getMessage(), null, false));
        }
    }

    @GetMapping("/export")
    public ResponseEntity<?> export(@RequestParam(name = "type") String type) {
        try {
            HttpHeaders headers = new HttpHeaders();

            if (type.equals("pdf")) {
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData(entityName + "Export", entityName + "Export.pdf");
            } else {
                headers.set("Content-Type",
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
                var contentDisposition = ContentDisposition.builder("attachment")
                        .filename(entityName + "Export.xls").build();
                headers.setContentDisposition(contentDisposition);
            }

            byte[] fileData = service.export(type);

            return ResponseEntity.ok().headers(headers).body(fileData);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}*/