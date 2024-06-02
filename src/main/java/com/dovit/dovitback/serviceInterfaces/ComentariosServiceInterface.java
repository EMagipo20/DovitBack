package com.dovit.dovitback.serviceInterfaces;
import com.dovit.dovitback.model.Comentarios;
import java.util.List;

//-> DIEGO
public interface ComentariosServiceInterface {
    public List<Comentarios> findAllByProyectoId(Long idProyecto);
    public void Insert (Comentarios comentario);
    public void Update (Comentarios comentario);
    public void Delete (Long id);
    Long contarComentariosPorProyecto(Long idProyecto);
}
