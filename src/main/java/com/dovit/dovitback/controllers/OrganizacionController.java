package com.dovit.dovitback.controllers;

import com.dovit.dovitback.dtos.OrganizacionDto;
import com.dovit.dovitback.model.Organizacion;
import com.dovit.dovitback.model.Usuario;
import com.dovit.dovitback.serviceImplements.OrganizacionServiceImplements;
import com.dovit.dovitback.serviceImplements.UsuarioServiceImplements;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Organizaciones")
@PreAuthorize("hasAnyAuthority('Organizacion')")
public class OrganizacionController {
    @Autowired
    private OrganizacionServiceImplements oS;

    @Autowired
    private UsuarioServiceImplements uS;

    @PostMapping("/Agregar")
    public ResponseEntity<String> registrar(@RequestBody OrganizacionDto dto) {
        ModelMapper modelMapper = new ModelMapper();
        Organizacion organizacion = modelMapper.map(dto, Organizacion.class);

        // Obtener y asignar el usuario
        Usuario usuario = uS.listarId(dto.getUsuarioId());
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }
        organizacion.setUsuario(usuario);

        oS.Insertar(organizacion);
        return ResponseEntity.ok("Organizacion creada exitosamente");
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<String> actualizar(@RequestBody OrganizacionDto dto) {
        Organizacion organizacion = oS.ListarId(dto.getId());
        if (organizacion == null) {
            return ResponseEntity.badRequest().body("Organización no encontrada");
        }

        organizacion.setNombreOrganizacion(dto.getNombreOrganizacion());
        organizacion.setDepartamento(dto.getDepartamento());
        organizacion.setDistrito(dto.getDistrito());
        organizacion.setDireccion(dto.getDireccion());
        organizacion.setCantidadProyectos(dto.getCantidadProyectos());
        organizacion.setNombreRepresentante(dto.getNombreRepresentante());
        organizacion.setNumeroTelefonicoRepresentante(dto.getNumeroTelefonicoRepresentante());
        organizacion.setCorreoElectronicoRepresentante(dto.getCorreoElectronicoRepresentante());

        // Obtener y asignar el usuario
        Usuario usuario = uS.listarId(dto.getUsuarioId());
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }
        organizacion.setUsuario(usuario);

        oS.Actualizar(organizacion);
        return ResponseEntity.ok("Organización actualizada exitosamente");
    }

    @GetMapping("/ListarTodo")
    public ResponseEntity<List<OrganizacionDto>> listarTodo() {
        List<OrganizacionDto> lista = oS.ListarTodo().stream().map(org -> {
            OrganizacionDto dto = new OrganizacionDto();
            dto.setId(org.getId());
            dto.setNombreOrganizacion(org.getNombreOrganizacion());
            dto.setDepartamento(org.getDepartamento());
            dto.setDistrito(org.getDistrito());
            dto.setDireccion(org.getDireccion());
            dto.setCantidadProyectos(org.getCantidadProyectos());
            dto.setNombreRepresentante(org.getNombreRepresentante());
            dto.setNumeroTelefonicoRepresentante(org.getNumeroTelefonicoRepresentante());
            dto.setCorreoElectronicoRepresentante(org.getCorreoElectronicoRepresentante());
            dto.setUsuarioId(org.getUsuario().getId());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/Buscar/{departamento}/{distrito}")
    public ResponseEntity<List<OrganizacionDto>> ListarPorDepartamentoYDistrito(@PathVariable String departamento, @PathVariable String distrito) {
        List<OrganizacionDto> lista = oS.ListarDepartamentoAndDistrito(departamento, distrito).stream().map(org -> {
            OrganizacionDto dto = new OrganizacionDto();
            dto.setId(org.getId());
            dto.setNombreOrganizacion(org.getNombreOrganizacion());
            dto.setDepartamento(org.getDepartamento());
            dto.setDistrito(org.getDistrito());
            dto.setDireccion(org.getDireccion());
            dto.setCantidadProyectos(org.getCantidadProyectos());
            dto.setNombreRepresentante(org.getNombreRepresentante());
            dto.setNumeroTelefonicoRepresentante(org.getNumeroTelefonicoRepresentante());
            dto.setCorreoElectronicoRepresentante(org.getCorreoElectronicoRepresentante());
            dto.setUsuarioId(org.getUsuario().getId());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/BuscarDireccionOrganizacionPorDistrito/{distrito}")
    public ResponseEntity<List<OrganizacionDto>> listarPorDistrito(@PathVariable String distrito) {
        List<OrganizacionDto> listaFinal = oS.listarPorDistrito(distrito).stream().map(organizacion -> {
            OrganizacionDto dto = new OrganizacionDto();
            dto.setId(organizacion.getId());
            dto.setNombreOrganizacion(organizacion.getNombreOrganizacion());
            dto.setDireccion(organizacion.getDireccion());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listaFinal);
    }
}
