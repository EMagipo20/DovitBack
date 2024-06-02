package com.dovit.dovitback.serviceInterfaces;
import com.dovit.dovitback.dtos.Publicaciones_ForoDto;
import com.dovit.dovitback.model.Publicaciones_Foro;
import java.util.List;

//-> HITALO
public interface Publicaciones_ForoServiceInterface {
    public void Insert (Publicaciones_Foro publicaciones_foro);
    public void Update (Publicaciones_Foro publicaciones_foro);
    public void Delete (Long id);
    public List<Publicaciones_ForoDto> ListarTodo();
    List<Object[]> obtenerPublicacionesPorOrganizacion();
}
