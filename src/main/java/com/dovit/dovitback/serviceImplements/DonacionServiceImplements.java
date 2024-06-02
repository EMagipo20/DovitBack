package com.dovit.dovitback.serviceImplements;

import com.dovit.dovitback.dtos.DonacionDto;
import com.dovit.dovitback.model.Donaciones;
import com.dovit.dovitback.repositories.DonacionRepository;
import com.dovit.dovitback.serviceInterfaces.DonacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DonacionServiceImplements implements DonacionService {
    @Autowired
    private DonacionRepository donacionRepository;

    @Override
    public void InsertarDonacion(Donaciones donacion) {
        donacionRepository.save(donacion);
    }

    @Override
    public List<DonacionDto> ListarDonacionesPorIdDonante(Long idDonante) {
        List<DonacionDto> ListaFinalDeDonaciones = ListarDonaciones();
        List<DonacionDto> donacionPorIdDonante = new ArrayList<>();

        for (DonacionDto d : ListaFinalDeDonaciones) {
            if (d.getDonanteId() != null && d.getDonanteId().equals(idDonante)) {
                donacionPorIdDonante.add(d);
            }
        }
        return donacionPorIdDonante;
    }

    @Override
    public List<DonacionDto> ListarDonacionesPorIdProyecto(Long idProyecto) {
        List<DonacionDto> ListaFinalDeDonaciones = ListarDonaciones();
        List<DonacionDto> donacionPorIdProyecto = new ArrayList<>();

        for (DonacionDto d : ListaFinalDeDonaciones) {
            if (d.getProyectoId() != null && d.getProyectoId().equals(idProyecto)) {
                donacionPorIdProyecto.add(d);
            }
        }
        return donacionPorIdProyecto;
    }

    @Override
    public List<DonacionDto> ListarDonaciones() {
        List<DonacionDto> ListaFinalDeDonaciones = new ArrayList<>();
        List<Donaciones> donaciones = donacionRepository.findAll();

        for (Donaciones d : donaciones) {
            DonacionDto donacionDto = new DonacionDto();
            donacionDto.setId(d.getId());
            donacionDto.setCantidadMonetaria(d.getCantidadMonetaria());
            donacionDto.setFechaDonacion(d.getFechaDonacion());
            donacionDto.setMetodoDePago(d.getMetodoDePago());
            donacionDto.setDonanteId(d.getDonante() != null ? d.getDonante().getId() : null);
            donacionDto.setProyectoId(d.getProyectos() != null ? d.getProyectos().getId() : null);
            ListaFinalDeDonaciones.add(donacionDto);
        }
        return ListaFinalDeDonaciones;
    }

    @Override
    public List<Object[]> obtenerDonacionesPorProyecto() {
        return donacionRepository.contarDonacionesPorProyecto();
    }

    @Override
    public List<Donaciones> donaciones(Long idDonante) {
        return donacionRepository.donaciones(idDonante);
    }
}

