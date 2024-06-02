package com.dovit.dovitback.serviceInterfaces;

import com.dovit.dovitback.model.Donante;
import com.dovit.dovitback.model.Organizacion;

import java.util.List;

public interface DonanteServiceInterface {
    public void Insert(Donante donante);
    public void Update(Donante donante);
    public void Delete(Long id);
    public List<Donante> ListarTodo();
    public Long ContarDonantesPorNombreCompleto(String nombreCompleto);
    List<Donante> ListarporDepartamentoyDistrito(String departamento, String distrito);
    public Donante ListarId(Long idDonante);
    List<Donante> listarPorDistrito(String distrito);
}
