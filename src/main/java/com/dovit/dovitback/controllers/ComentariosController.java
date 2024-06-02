package com.dovit.dovitback.controllers;

import com.dovit.dovitback.dtos.ComentariosDto;
import com.dovit.dovitback.model.Comentarios;
import com.dovit.dovitback.model.Donante;
import com.dovit.dovitback.model.Proyectos;
import com.dovit.dovitback.serviceImplements.ComentariosImplements;
import com.dovit.dovitback.serviceImplements.DonanteServiceImplements;
import com.dovit.dovitback.serviceImplements.ProyectoServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//-> DIEGO
@RestController
@RequestMapping("/Comentarios")
@PreAuthorize("hasAnyAuthority('Donante')")
public class ComentariosController {

    @Autowired
    private final ComentariosImplements comentariosServiceImplements;

    @Autowired
    private ProyectoServiceImplements proyectoServiceImplements;

    @Autowired
    private DonanteServiceImplements donanteServiceImplements;

    public ComentariosController(ComentariosImplements comentariosServiceImplements, ProyectoServiceImplements proyectoServiceImplements, DonanteServiceImplements donanteServiceImplements) {
        this.comentariosServiceImplements = comentariosServiceImplements;
        this.proyectoServiceImplements = proyectoServiceImplements;
        this.donanteServiceImplements = donanteServiceImplements;
    }

    @PostMapping("/Agregar")
    public ResponseEntity<String> insertar(@RequestBody ComentariosDto comentariosDto) {
        //Obtener y asignar el proyecto
        Proyectos proyecto = proyectoServiceImplements.ListarId(comentariosDto.getProyectoId());
        if (proyecto == null || proyecto.getId() == null) {
            return ResponseEntity.badRequest().body("Proyecto no encontrado");
        }

        //Obtener y asignar el donante
        Donante donante = donanteServiceImplements.ListarId(comentariosDto.getDonanteId());
        if (donante == null || donante.getId() == null) {
            return ResponseEntity.badRequest().body("Donante no encontrado");
        }

        Comentarios comentario = new Comentarios();
        comentario.setTitulo(comentariosDto.getTitulo());
        comentario.setDescripcion(comentariosDto.getDescripcion());
        comentario.setProyecto(proyecto);
        comentario.setDonante(donante);

        comentariosServiceImplements.Insert(comentario);
        return ResponseEntity.ok("Comentario creado exitosamente");
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<String> actualizar(@RequestBody ComentariosDto comentariosDto) {
        // Obtener y asignar el proyecto
        Proyectos proyecto = proyectoServiceImplements.ListarId(comentariosDto.getProyectoId());
        if (proyecto == null || proyecto.getId() == null) {
            return ResponseEntity.badRequest().body("Proyecto no encontrado");
        }

        // Obtener y asignar el donante
        Donante donante = donanteServiceImplements.ListarId(comentariosDto.getDonanteId());
        if (donante == null || donante.getId() == null) {
            return ResponseEntity.badRequest().body("Donante no encontrado");
        }

        Comentarios comentario = new Comentarios();
        comentario.setId(comentariosDto.getId());
        comentario.setTitulo(comentariosDto.getTitulo());
        comentario.setDescripcion(comentariosDto.getDescripcion());
        comentario.setProyecto(proyecto);
        comentario.setDonante(donante);

        comentariosServiceImplements.Update(comentario);
        return ResponseEntity.ok("Comentario actualizado exitosamente");
    }

    @DeleteMapping("/Eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        comentariosServiceImplements.Delete(id);
        return ResponseEntity.ok("Comentario eliminado exitosamente");
    }

    @GetMapping("/ListarPorId/{idProyecto}")
    public ResponseEntity<List<Comentarios>> findAllByProyectoId(@PathVariable Long idProyecto) {
        List<Comentarios> listaFinal = comentariosServiceImplements.findAllByProyectoId(idProyecto);
        return ResponseEntity.ok(listaFinal);
    }

    @GetMapping("/ContarComentariosPorProyecto/{idProyecto}")
    public ResponseEntity<Long> contarComentariosPorProyecto(@PathVariable("idProyecto") Long idProyecto) {
        Long cantidadComentarios = comentariosServiceImplements.contarComentariosPorProyecto(idProyecto);
        return ResponseEntity.ok(cantidadComentarios);
    }
}
