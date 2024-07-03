package com.Corhuila.backend_security.Security.Controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Corhuila.backend_security.Dto.ApiResponseDto;
import com.Corhuila.backend_security.IRepository.IFormRepository;
import com.Corhuila.backend_security.IRepository.IModuleRepository;
import com.Corhuila.backend_security.Security.Dto.JwtDto;
import com.Corhuila.backend_security.Security.Dto.UserDto;
import com.Corhuila.backend_security.Security.Entity.Role;
import com.Corhuila.backend_security.Security.Entity.User;
import com.Corhuila.backend_security.Security.Jwt.JwtProvider;
import com.Corhuila.backend_security.Security.Service.RolService;
import com.Corhuila.backend_security.Security.Service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    IModuleRepository moduleRepository;
    
    @Autowired
    IFormRepository formRepository;

    @PostMapping("/nuevo")
    public ResponseEntity<ApiResponseDto<String>> nuevo(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<ApiResponseDto<String>>(new ApiResponseDto<String>("campos mal puestos o email inválido", null, false), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByUsername(userDto.getUsername()))
            return new ResponseEntity<ApiResponseDto<String>>(new ApiResponseDto<String>("Ese nombre ya existe", null, false), HttpStatus.BAD_REQUEST);
        
        User usuario = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()));
        
        Set<Role> roles = new HashSet<>();
        
        userDto.getRoles().forEach(role -> {
        	roles.add(rolService.getById(role).get());
        });
        
        usuario.setRoles(roles);
        usuario.setCreatedAt(LocalDateTime.now());
        
        usuarioService.save(usuario);
        
        return new ResponseEntity<ApiResponseDto<String>>(new ApiResponseDto<String>("usuario guardado", null, true), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<UserDto>> login(@RequestBody UserDto userDto, BindingResult bindingResult){
        try {
        	if(bindingResult.hasErrors()) return new ResponseEntity<ApiResponseDto<UserDto>>(new ApiResponseDto<UserDto>("Campos mal puestos", null, false), HttpStatus.BAD_REQUEST);
			
        	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            
            Optional<User> userO = usuarioService.getByUsername(userDto.getUsername());
            User user = userO.get();
            
            userDto.setId(user.getId());
            userDto.setToken(jwt);
            userDto.setModulos(moduleRepository.findByUserId(user.getId()));
            userDto.setFormularios(formRepository.getFormsByUser(user.getId()));
            
            return new ResponseEntity<ApiResponseDto<UserDto>>(new ApiResponseDto<UserDto>("Sesión iniciada", userDto, true), HttpStatus.OK);
        	
		} catch (Exception e) {
			return new ResponseEntity<ApiResponseDto<UserDto>>(new ApiResponseDto<UserDto>(e.getMessage(), null, false), HttpStatus.BAD_REQUEST);
		}
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity<JwtDto>(jwt, HttpStatus.OK);
    }
}
