package com.dovit.dovitback.controllers;

import com.dovit.dovitback.dtos.ProyectoDto;
import com.dovit.dovitback.model.Organizacion;
import com.dovit.dovitback.model.Proyectos;
import com.dovit.dovitback.serviceImplements.OrganizacionServiceImplements;
import com.dovit.dovitback.serviceImplements.ProyectoServiceImplements;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//-> LUIS ANGEL
@RestController
@RequestMapping("/Proyectos")
@PreAuthorize("hasAuthority('Organizacion')")
public class ProyectoController {
    @Autowired
    private final ProyectoServiceImplements proyectoServiceImplements;

    @Autowired
    private OrganizacionServiceImplements oS;

    public ProyectoController(ProyectoServiceImplements proyectoServiceImplements) {
        this.proyectoServiceImplements = proyectoServiceImplements;
    }

    @PostMapping("/Agregar")
    public ResponseEntity<String> registrar(@RequestBody ProyectoDto proyectoDto) {
        ModelMapper c = new ModelMapper();
        Proyectos p = c.map(proyectoDto, Proyectos.class);

        // Obtener y asignar la organización
        Organizacion organizacion = oS.ListarId(proyectoDto.getOrganizacionBeneficaId());
        if (organizacion == null || organizacion.getId() == null) {
            return ResponseEntity.badRequest().body("Organización benéfica no encontrada");
        }
        p.setOrganizacion(organizacion);

        proyectoServiceImplements.insertar(p);
        return ResponseEntity.ok("Proyecto creado exitosamente");
    }

    @GetMapping("/ListarPorOrganizacion/{idOrganizacion}")
    public ResponseEntity<List<ProyectoDto>> listarPorOrganizacionId(@PathVariable Long idOrganizacion) {
        List<Proyectos> proyectos = proyectoServiceImplements.ListarPorProyectoId(idOrganizacion);
        ModelMapper modelMapper = new ModelMapper();
        List<ProyectoDto> proyectoDtos = proyectos.stream()
                .map(proyecto -> {
                    ProyectoDto dto = modelMapper.map(proyecto, ProyectoDto.class);
                    if (proyecto.getOrganizacion() != null) {
                        dto.setOrganizacionBeneficaId(proyecto.getOrganizacion().getId());
                        dto.setMonto_Objetivo(proyecto.getMontoObjetivo());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(proyectoDtos);
    }

    @GetMapping("/ListarTodo")
    public ResponseEntity<List<ProyectoDto>> listarTodo() {
        List<Proyectos> proyectos = proyectoServiceImplements.listarTodo();
        ModelMapper modelMapper = new ModelMapper();
        List<ProyectoDto> proyectoDtos = proyectos.stream()
                .map(proyecto -> {
                    ProyectoDto dto = new ProyectoDto();
                    dto.setId(proyecto.getId());
                    dto.setTitulo(proyecto.getTitulo());
                    dto.setDescripcion(proyecto.getDescripcion());
                    dto.setMonto_Objetivo(proyecto.getMontoObjetivo());
                    dto.setFechaInicio(proyecto.getFechaInicio());
                    dto.setFechaFin(proyecto.getFechaFin());
                    dto.setCategoria(proyecto.getCategoria());
                    dto.setOrganizacionBeneficaId(proyecto.getOrganizacion().getId());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(proyectoDtos);
    }

    @GetMapping("/proyectosPorCategoria")
    public ResponseEntity<List<Object[]>> obtenerProyectosPorCategoria() {
        return ResponseEntity.ok(proyectoServiceImplements.obtenerProyectosPorCategoria());
    }
}