package com.dovit.dovitback.serviceImplements;

import com.dovit.dovitback.model.Donante;
import com.dovit.dovitback.model.Organizacion;
import com.dovit.dovitback.repositories.DonanteRepository;
import com.dovit.dovitback.serviceInterfaces.DonanteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonanteServiceImplements implements DonanteServiceInterface {
    @Autowired
    private DonanteRepository dR;

    @Override
    public void Insert(Donante donante) { dR.save(donante); }

    @Override
    public void Update(Donante donante) { dR.save(donante); }

    @Override
    public void Delete(Long id) { dR.deleteById(id); }

    @Override
    public List<Donante> ListarTodo() { return (List<Donante>) dR.findAll(); }

    @Override
    public Long ContarDonantesPorNombreCompleto(String nombreCompleto) {
        return dR.ContarDonantesPorNombreCompleto(nombreCompleto);
    }

    @Override
    public List<Donante> ListarporDepartamentoyDistrito(String departamento, String distrito) {
        return dR.findByDepartamentoAndDistrito(departamento, distrito);
    }

    @Override
    public Donante ListarId(Long idDonante) {
        return dR.findById(idDonante).orElse(new Donante());
    }

    @Override
    public List<Donante> listarPorDistrito(String distrito) {
        return dR.findByDistrito(distrito);
    }
}
