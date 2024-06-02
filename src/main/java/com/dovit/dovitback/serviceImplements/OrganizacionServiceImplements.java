package com.dovit.dovitback.serviceImplements;

import com.dovit.dovitback.model.Organizacion;
import com.dovit.dovitback.repositories.OrganizacionRepository;
import com.dovit.dovitback.serviceInterfaces.OrganizacionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizacionServiceImplements implements OrganizacionServiceInterface{

    @Autowired
    private OrganizacionRepository oR;

    @Override
    public void Insertar(Organizacion organizacion) {
        oR.save(organizacion);
    }

    @Override
    public void Actualizar(Organizacion organizacion) {
            oR.save(organizacion);
    }

    @Override
    public List<Organizacion> ListarTodo() {
        return (List<Organizacion>) oR.findAll();
    }

    @Override
    public List<Organizacion> ListarDepartamentoAndDistrito(String departamento, String distrito) {
        return oR.findByDepartamentoAndDistrito(departamento, distrito);
    }

    @Override
    public Organizacion ListarId(Long idOrganizacion) {
            return oR.findById(idOrganizacion).orElse(new Organizacion());
    }

    @Override
    public List<Organizacion> listarPorDistrito(String distrito) {
        return oR.findByDistrito(distrito);
    }
}
