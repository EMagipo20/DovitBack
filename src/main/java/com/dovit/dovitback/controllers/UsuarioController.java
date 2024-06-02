package com.dovit.dovitback.controllers;

import com.dovit.dovitback.dtos.UsuarioDto;
import com.dovit.dovitback.model.Usuario;
import com.dovit.dovitback.serviceInterfaces.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService uS;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void registrar(@RequestBody UsuarioDto dto) {
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);
        String encodedPassword = passwordEncoder.encode(u.getPassword());
        u.setPassword(encodedPassword);
        uS.insert(u);
    }

    @PutMapping
    public void modificar(@RequestBody UsuarioDto dto) {
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);
        uS.insert(u);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id) {
        uS.delete(id);
    }

    @GetMapping("/{id}")
    public UsuarioDto listarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        UsuarioDto dto = m.map(uS.listarId(id), UsuarioDto.class);
        return dto;
    }

    @GetMapping
    public List<UsuarioDto> listar() {
        return uS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioDto.class);
        }).collect(Collectors.toList());
    }
}
