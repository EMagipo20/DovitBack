package com.dovit.dovitback.serviceImplements;
import com.dovit.dovitback.model.Comentarios;
import com.dovit.dovitback.repositories.ComentariosRepository;
import com.dovit.dovitback.serviceInterfaces.ComentariosServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//-> DIEGO
@Service
public class ComentariosImplements implements ComentariosServiceInterface {

    @Autowired
    private ComentariosRepository cR;

    //-> Listar todos los Comentarios por ID Proyecto
    @Override
    public List<Comentarios> findAllByProyectoId(Long idProyecto) {
        return cR.findAllByProyectoId(idProyecto);
    }

    //-> Insertar nuevo Comentario
    @Override
    public void Insert(Comentarios comentario) {
        cR.save(comentario);
    }

    //-> Actualizar Comentario
    @Override
    public void Update(Comentarios comentario) { cR.save(comentario); }

    //-> Eliminar Comentario
    @Override
    public void Delete(Long id) { cR.deleteById(id); }

    @Override
    public Long contarComentariosPorProyecto(Long idProyecto) {
        return cR.countByProyectoId(idProyecto);
    }
}
