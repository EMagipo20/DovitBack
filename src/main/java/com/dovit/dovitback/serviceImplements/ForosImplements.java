package com.dovit.dovitback.serviceImplements;
import com.dovit.dovitback.dtos.ForosDto;
import com.dovit.dovitback.model.Foros;
import com.dovit.dovitback.repositories.ForosRepository;
import com.dovit.dovitback.serviceInterfaces.ForosServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

//-> YAN SOTO
@Service
public class ForosImplements implements ForosServiceInterface {
    @Autowired
    private ForosRepository fR;

    @Override
    public void insertarForo(Foros foro) { fR.save(foro); }

    @Override
    public List<ForosDto> ListarForos() {
        List<ForosDto> listaFinalForos = new ArrayList<>();
        List<Foros> foros = fR.findAll();
        ForosDto foro;

        for (Foros d : foros) {
            foro = new ForosDto();
            foro.setId(d.getId());
            foro.setTitulo(d.getTitulo());
            foro.setProyectoId(d.getProyecto().getId());
            listaFinalForos.add(foro);
        }
        return listaFinalForos;
    }

    @Override
    public List<ForosDto> ListarForosPornombreProyecto(String nombreProyecto) {
        List<ForosDto> ListarForosPorNombreProyecto = new ArrayList<>();
        List<Foros> foros = fR.findAll();
        ForosDto foro;

        for (Foros d : foros) {
            if(d.getProyecto().getTitulo().equals(nombreProyecto)){
                foro = new ForosDto();
                foro.setId(d.getId());
                foro.setTitulo(d.getTitulo());
                foro.setProyectoId(d.getProyecto().getId());
                ListarForosPorNombreProyecto.add(foro);

            }
        }
        return ListarForosPorNombreProyecto;
    }

    @Override
    public Foros ListarId(Long idForo) {
        return fR.findById(idForo).orElse(new Foros());
    }

    @Override
    public List<Object[]> obtenerForosPorProyecto() {
        return fR.contarForosPorProyecto();
    }
}
