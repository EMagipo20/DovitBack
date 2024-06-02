package com.dovit.dovitback.serviceInterfaces;
import com.dovit.dovitback.dtos.ForosDto;
import com.dovit.dovitback.model.Foros;
import com.dovit.dovitback.model.Organizacion;

import java.util.List;

//-> YAN SOTO
public interface ForosServiceInterface {
    void insertarForo(Foros foro);
    public List<ForosDto> ListarForos();
    public Foros ListarId(Long idForo);
    List<Object[]> obtenerForosPorProyecto();
    List<ForosDto> ListarForosPornombreProyecto(String nombreProyecto);
}
