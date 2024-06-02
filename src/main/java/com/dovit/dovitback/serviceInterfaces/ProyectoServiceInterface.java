package com.dovit.dovitback.serviceInterfaces;

import com.dovit.dovitback.model.Comentarios;
import com.dovit.dovitback.model.Proyectos;
import java.util.List;

//-> KIARA
public interface ProyectoServiceInterface {
    public void insertar(Proyectos proyectos);
    public List<Proyectos> ListarPorProyectoId(Long idOrganizacion);
    public Proyectos ListarId(Long idProyecto);
    List<Proyectos> listarTodo();
    List<Object[]> obtenerProyectosPorCategoria();
}