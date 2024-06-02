package com.dovit.dovitback.controllers;

import com.dovit.dovitback.dtos.ForosDto;
import com.dovit.dovitback.model.Foros;
import com.dovit.dovitback.model.Proyectos;
import com.dovit.dovitback.serviceImplements.ForosImplements;
import com.dovit.dovitback.serviceImplements.ProyectoServiceImplements;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//-> YAN SOTO
@RestController
@RequestMapping("/Foros")
@PreAuthorize("hasAnyAuthority('Organizacion')")
public class ForosController {
    @Autowired
    private final ForosImplements fI;

    @Autowired
    private ProyectoServiceImplements pSI;

    public ForosController(ForosImplements fI, ProyectoServiceImplements pSI) {
        this.pSI = pSI;
        this.fI = fI;
    }

    @PostMapping("/Agregar")
    public ResponseEntity<String> Registrar(@RequestBody ForosDto forosDto) {
        ModelMapper c = new ModelMapper();
        Foros f = c.map(forosDto, Foros.class);

        //Obtener y asignar el proyecto
        Proyectos proyecto = pSI.ListarId(forosDto.getProyectoId());
        if (proyecto == null || proyecto.getId() == null) {
            return ResponseEntity.badRequest().body("Proyecto no encontrado");
        }

        Foros foros = new Foros();
        foros.setTitulo(forosDto.getTitulo());
        foros.setProyecto(proyecto);

        fI.insertarForo(f);
        return ResponseEntity.ok("Foro creado exitosamente");
    }

    @GetMapping("/ListarForosPorNombreProyecto/{nombreProyecto}")
    public ResponseEntity<List<ForosDto>> ListarForosPorNombreProyecto(@PathVariable("nombreProyecto") String nombreProyecto) {
        List<ForosDto> listaFinal = fI.ListarForosPornombreProyecto(nombreProyecto);
        return ResponseEntity.ok(listaFinal);
    }

    @GetMapping("/ListarTodo")
    public ResponseEntity<List<ForosDto>> ListarForos() {
        List<ForosDto> listaFinal = fI.ListarForos();
        return ResponseEntity.ok(listaFinal);
    }

    @GetMapping("/forosPorProyecto")
    public ResponseEntity<List<Object[]>> obtenerForosPorProyecto() {
        return ResponseEntity.ok(fI.obtenerForosPorProyecto());
    }
}
