package com.dovit.dovitback.serviceImplements;
import com.dovit.dovitback.dtos.Publicaciones_ForoDto;
import com.dovit.dovitback.model.Publicaciones_Foro;
import com.dovit.dovitback.repositories.Publicaciones_ForoRepository;
import com.dovit.dovitback.serviceInterfaces.Publicaciones_ForoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

//-> HITALO
@Service
public class Publicaciones_ForoImplements implements Publicaciones_ForoServiceInterface {

    @Autowired
    private Publicaciones_ForoRepository publicaciones_foroRepository;

    //-> Insertar nuevo [Publicaciones_Foro]
    @Override
    public void Insert(Publicaciones_Foro publicaciones_foro) {
        publicaciones_foroRepository.save(publicaciones_foro);
    }

    //-> Actualizar [Publicaciones_Foro]
    @Override
    public void Update(Publicaciones_Foro publicaciones_foro) {
        publicaciones_foroRepository.save(publicaciones_foro);
    }

    //-> Eliminar [Publicaciones_Foro]
    @Override
    public void Delete(Long id) {
        publicaciones_foroRepository.deleteById(id);
    }

    //-> Listar todos los [Publicaciones_Foro]
    @Override
    public List<Publicaciones_ForoDto> ListarTodo() {
        List<Publicaciones_Foro> publicaciones_foros = publicaciones_foroRepository.findAll();
        List<Publicaciones_ForoDto> listaFinalDto = new ArrayList<>();
        Publicaciones_ForoDto p_f;
        for (Publicaciones_Foro d : publicaciones_foros) {
            p_f = new Publicaciones_ForoDto();
            p_f.setId(d.getId());
            p_f.setTitulo(d.getTitulo());
            p_f.setContenido(d.getContenido());
            p_f.setOrganizacionId(d.getOrganizacion().getId());
            p_f.setForosId(d.getForos().getId());
            listaFinalDto.add(p_f);
        }
        return listaFinalDto;
    }

    @Override
    public List<Object[]> obtenerPublicacionesPorOrganizacion() {
        return publicaciones_foroRepository.contarPublicacionesPorOrganizacion();
    }
}
