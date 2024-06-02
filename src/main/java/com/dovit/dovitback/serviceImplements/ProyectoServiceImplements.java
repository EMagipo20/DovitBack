package com.dovit.dovitback.serviceImplements;

import com.dovit.dovitback.model.Proyectos;
import com.dovit.dovitback.repositories.ProyectoRepository;
import com.dovit.dovitback.serviceInterfaces.ProyectoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//-> KIARA
@Service
public class ProyectoServiceImplements implements ProyectoServiceInterface {
    @Autowired
    private ProyectoRepository proyectoRepository;

    @Override
    public void insertar(Proyectos proyectos) {
        proyectoRepository.save(proyectos);
    }

    @Override
    public Proyectos ListarId(Long idProyecto) {
        return proyectoRepository.findById(idProyecto).orElse(new Proyectos());
    }

    @Override
    public List<Object[]> obtenerProyectosPorCategoria() {
        return proyectoRepository.contarProyectosPorCategoria();
    }

    @Override
    public List<Proyectos> listarTodo() {
        return proyectoRepository.findAll();
    }

    @Override
    public List<Proyectos> ListarPorProyectoId(Long idOrganizacion) {
        return proyectoRepository.BuscarPorProyectoId(idOrganizacion);
    }
}