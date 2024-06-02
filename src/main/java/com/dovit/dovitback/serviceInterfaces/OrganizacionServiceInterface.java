package com.dovit.dovitback.serviceInterfaces;

import com.dovit.dovitback.model.Organizacion;
import com.dovit.dovitback.model.Usuario;

import java.util.List;

public interface OrganizacionServiceInterface {
    public void Insertar(Organizacion organizacion);
    public void Actualizar(Organizacion organizacion);
    public List<Organizacion> ListarTodo();
    public List<Organizacion> ListarDepartamentoAndDistrito (String departamento, String distrito);
    public Organizacion ListarId(Long idOrganizacion);
    public List<Organizacion> listarPorDistrito(String distrito);
}
