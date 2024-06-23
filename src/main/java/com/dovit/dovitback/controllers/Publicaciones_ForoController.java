package com.dovit.dovitback.controllers;

import com.dovit.dovitback.dtos.Publicaciones_ForoDto;
import com.dovit.dovitback.model.Foros;
import com.dovit.dovitback.model.Organizacion;
import com.dovit.dovitback.model.Publicaciones_Foro;
import com.dovit.dovitback.serviceImplements.ForosImplements;
import com.dovit.dovitback.serviceImplements.OrganizacionServiceImplements;
import com.dovit.dovitback.serviceInterfaces.Publicaciones_ForoServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//-> HITALO
@RestController
@RequestMapping("/Publicaciones_Foro")
@PreAuthorize("hasAnyAuthority('Organizacion')")
public class Publicaciones_ForoController {
    @Autowired
    private Publicaciones_ForoServiceInterface pS;

    @Autowired
    private OrganizacionServiceImplements oS;

    @Autowired
    private ForosImplements fI;

    public Publicaciones_ForoController(Publicaciones_ForoServiceInterface pS, OrganizacionServiceImplements oS, ForosImplements fI) {
        this.pS = pS;
        this.oS = oS;
        this.fI = fI;
    }

    @PostMapping("/Agregar")
    public ResponseEntity<String> Registrar(@RequestBody Publicaciones_ForoDto dto) {
        ModelMapper modelMapper = new ModelMapper();
        Publicaciones_Foro publicaciones_foro = modelMapper.map(dto, Publicaciones_Foro.class);
        pS.Insert(publicaciones_foro);
        return ResponseEntity.ok().body("Publicación de foro creada exitosamente");
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<String> Update(@RequestBody Publicaciones_ForoDto dto) {
        // Crear y asignar la organización
        Organizacion organizacion = oS.ListarId(dto.getOrganizacionId());
        if (organizacion == null || organizacion.getId() == null) {
            return ResponseEntity.badRequest().body("Organización benéfica no encontrada");
        }

        // Crear y asignar el foro
        Foros foros = fI.ListarId(dto.getForosId());
        if (foros == null || foros.getId() == null) {
            return ResponseEntity.badRequest().body("Foro no encontrado");
        }

        // Mapear Publicaciones_ForoDto a Publicaciones_Foro
        Publicaciones_Foro p = new Publicaciones_Foro();
        p.setId(dto.getId());
        p.setTitulo(dto.getTitulo());
        p.setContenido(dto.getContenido());
        p.setOrganizacion(organizacion);
        p.setForos(foros);

        pS.Update(p);
        return ResponseEntity.ok().body("Publicación de foro actualizada exitosamente");
    }

    @DeleteMapping("/Eliminar/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        pS.Delete(id);
        return ResponseEntity.ok().body("Publicación de foro eliminada exitosamente");
    }

    @GetMapping("/ListarTodo")
    public ResponseEntity<List<Publicaciones_ForoDto>> list() {
        List<Publicaciones_ForoDto> lista = pS.ListarTodo().stream().map(p -> {
            Publicaciones_ForoDto dto = new Publicaciones_ForoDto();
            dto.setId(p.getId());
            dto.setTitulo(p.getTitulo());
            dto.setContenido(p.getContenido());
            dto.setOrganizacionId(p.getOrganizacionId());
            dto.setForosId(p.getForosId());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/publicacionesPorOrganizacion")
    public ResponseEntity<List<Object[]>> obtenerPublicacionesPorOrganizacion() {
        return ResponseEntity.ok(pS.obtenerPublicacionesPorOrganizacion());
    }
}

