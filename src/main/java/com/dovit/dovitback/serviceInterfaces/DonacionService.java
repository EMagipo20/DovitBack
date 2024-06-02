package com.dovit.dovitback.serviceInterfaces;
import com.dovit.dovitback.dtos.DonacionDto;
import com.dovit.dovitback.model.Donaciones;
import java.util.List;

public interface DonacionService {
    public void InsertarDonacion(Donaciones donacion);
    public List<DonacionDto> ListarDonacionesPorIdDonante(Long idDonante);
    public List<DonacionDto> ListarDonacionesPorIdProyecto(Long idProyecto);
    public List<DonacionDto> ListarDonaciones();
    public List<Donaciones> donaciones(Long idDonante);
    List<Object[]> obtenerDonacionesPorProyecto();
}
