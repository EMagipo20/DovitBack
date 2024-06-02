package com.dovit.dovitback.controllers;
import com.dovit.dovitback.dtos.DonacionDto;
import com.dovit.dovitback.model.*;
import com.dovit.dovitback.repositories.DonacionRepository;
import com.dovit.dovitback.serviceImplements.DonacionServiceImplements;
import com.dovit.dovitback.serviceImplements.DonanteServiceImplements;
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
@RequestMapping("/Donaciones")
@PreAuthorize("hasAuthority('Donante')")
public class DonacionController {
    @Autowired
    private final DonacionServiceImplements donacionServiceImplements;

    @Autowired
    private DonanteServiceImplements dSI;

    @Autowired
    private ProyectoServiceImplements pSI;

    public DonacionController(DonacionServiceImplements donacionServiceImplements, ProyectoServiceImplements proyectoServiceImplements) {
        this.donacionServiceImplements = donacionServiceImplements;
        this.pSI = proyectoServiceImplements;
    }

    @PostMapping("/Agregar")
    public ResponseEntity<String> registrar(@RequestBody DonacionDto donacionDto) {
        ModelMapper modelMapper = new ModelMapper();
        Donaciones donacion = modelMapper.map(donacionDto, Donaciones.class);

        // Obtener y asignar el donante
        Donante donante = dSI.ListarId(donacionDto.getDonanteId());
        if (donante == null || donante.getId() == null) {
            return ResponseEntity.badRequest().body("Donante no encontrado");
        }
        donacion.setDonante(donante);

        // Obtener y asignar el proyecto
        Proyectos proyecto = pSI.ListarId(donacionDto.getProyectoId());
        if (proyecto == null || proyecto.getId() == null) {
            return ResponseEntity.badRequest().body("Proyecto no encontrado");
        }
        donacion.setProyectos(proyecto);

        donacionServiceImplements.InsertarDonacion(donacion);
        return ResponseEntity.ok("Donacion creada exitosamente");
    }

    @GetMapping("/ListarDonacionesPorIdDonante/{idDonante}")
    public ResponseEntity<List<DonacionDto>> ListarDonacionesPorIdDonante(@PathVariable("idDonante") Long idDonante) {
        List<DonacionDto> listaFinal = donacionServiceImplements.ListarDonacionesPorIdDonante(idDonante);
        return ResponseEntity.ok(listaFinal);
    }

    @GetMapping("/ListarDonacionesPorIdProyecto/{idProyectos}")
    public ResponseEntity<List<DonacionDto>> ListarDonacionesPorIdProyecto(@PathVariable("idProyectos") Long idProyectos) {
        List<DonacionDto> listaFinal = donacionServiceImplements.ListarDonacionesPorIdProyecto(idProyectos);
        return ResponseEntity.ok(listaFinal);
    }

    @GetMapping("/ListarDonacionesPorIdProyectoEnEsteAnio/{idDonante}")
    public ResponseEntity<List<DonacionDto>> listarDonacionesPorIdProyectoEnEsteAnio(@PathVariable("idDonante") Long idDonante) {
        List<DonacionDto> listaFinal = donacionServiceImplements.donaciones(idDonante).stream().map(donacion -> {
            DonacionDto dto = new DonacionDto();
            dto.setId(donacion.getId());
            dto.setCantidadMonetaria(donacion.getCantidadMonetaria());
            dto.setFechaDonacion(donacion.getFechaDonacion());
            dto.setMetodoDePago(donacion.getMetodoDePago());
            dto.setDonanteId(donacion.getDonante().getId());
            dto.setProyectoId(donacion.getProyectos().getId());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listaFinal);
    }

    @GetMapping("/DonacionesPorProyecto")
    public ResponseEntity<List<Object[]>> obtenerDonacionesPorProyecto() {
        return ResponseEntity.ok(donacionServiceImplements.obtenerDonacionesPorProyecto());
    }
}
