package com.dovit.dovitback.controllers;

import com.dovit.dovitback.dtos.DonanteDto;
import com.dovit.dovitback.model.Donante;
import com.dovit.dovitback.model.Usuario;
import com.dovit.dovitback.serviceImplements.DonanteServiceImplements;
import com.dovit.dovitback.serviceImplements.UsuarioServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Donantes")
@PreAuthorize("hasAnyAuthority('Donante')")
public class DonanteController {
    @Autowired
    private DonanteServiceImplements dS;

    @Autowired
    private UsuarioServiceImplements uS;

    @PostMapping("/Agregar")
    public ResponseEntity<String> registrar(@RequestBody DonanteDto dto) {
        Donante donante = new Donante();
        donante.setNombreCompleto(dto.getNombreCompleto());
        donante.setContactoTelefonico(dto.getContactoTelefonico());
        donante.setDepartamento(dto.getDepartamento());
        donante.setDistrito(dto.getDistrito());
        donante.setDireccion(dto.getDireccion());

        // Obtener y asignar el usuario
        Usuario usuario = uS.listarId(dto.getUsuarioId());
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }
        donante.setUsuario(usuario);

        dS.Insert(donante);
        return ResponseEntity.ok("Donante creado exitosamente");
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<String> actualizar(@RequestBody DonanteDto dto) {
        Donante donante = dS.ListarId(dto.getId());
        if (donante == null) {
            return ResponseEntity.badRequest().body("Donante no encontrado");
        }

        donante.setNombreCompleto(dto.getNombreCompleto());
        donante.setContactoTelefonico(dto.getContactoTelefonico());
        donante.setDepartamento(dto.getDepartamento());
        donante.setDistrito(dto.getDistrito());
        donante.setDireccion(dto.getDireccion());

        // Obtener y asignar el usuario
        Usuario usuario = uS.listarId(dto.getUsuarioId());
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }
        donante.setUsuario(usuario);

        dS.Update(donante);
        return ResponseEntity.ok("Donante actualizado exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
        Donante donante = dS.ListarId(id);
        if (donante == null) {
            return ResponseEntity.badRequest().body("Donante no encontrado");
        }
        dS.Delete(id);
        return ResponseEntity.ok("Donante eliminado exitosamente");
    }

    @GetMapping("/ListarTodo")
    public ResponseEntity<List<DonanteDto>> list() {
        List<DonanteDto> lista = dS.ListarTodo().stream().map(y -> {
            DonanteDto dto = new DonanteDto();
            dto.setId(y.getId());
            dto.setNombreCompleto(y.getNombreCompleto());
            dto.setContactoTelefonico(y.getContactoTelefonico());
            dto.setDepartamento(y.getDepartamento());
            dto.setDistrito(y.getDistrito());
            dto.setDireccion(y.getDireccion());
            dto.setUsuarioId(y.getUsuario().getId());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/ContarPorNombreCompleto")
    public ResponseEntity<Long> contarPorNombreCompleto(@RequestParam String nombreCompleto) {
        Long count = dS.ContarDonantesPorNombreCompleto(nombreCompleto);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/Buscar/{departamento}/{distrito}")
    public ResponseEntity<List<DonanteDto>> listarPorDepartamentoYDistrito(@PathVariable String departamento, @PathVariable String distrito) {
        List<DonanteDto> lista = dS.ListarporDepartamentoyDistrito(departamento, distrito).stream().map(y -> {
            DonanteDto dto = new DonanteDto();
            dto.setId(y.getId());
            dto.setNombreCompleto(y.getNombreCompleto());
            dto.setContactoTelefonico(y.getContactoTelefonico());
            dto.setDepartamento(y.getDepartamento());
            dto.setDistrito(y.getDistrito());
            dto.setDireccion(y.getDireccion());
            dto.setUsuarioId(y.getUsuario().getId());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/BuscarDireccionDonantePorDistrito/{distrito}")
    public ResponseEntity<List<DonanteDto>> listarPorDistrito(@PathVariable String distrito) {
        List<DonanteDto> listaFinal = dS.listarPorDistrito(distrito).stream().map(donante -> {
            DonanteDto dto = new DonanteDto();
            dto.setId(donante.getId());
            dto.setNombreCompleto(donante.getNombreCompleto());
            dto.setDireccion(donante.getDireccion());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listaFinal);
    }
}

