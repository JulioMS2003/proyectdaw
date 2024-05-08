package pe.edu.cibertec.proyectdaw.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.*;
import pe.edu.cibertec.proyectdaw.model.dto.request.ProyectoRequest;
import pe.edu.cibertec.proyectdaw.repository.AsignacionRepository;
import pe.edu.cibertec.proyectdaw.repository.PlanoRepository;
import pe.edu.cibertec.proyectdaw.repository.ProyectoRepository;

import java.util.*;

@AllArgsConstructor
@Service
public class ProyectoService implements IProyectoService{

    private ProyectoRepository proyectoRepository;
    private PlanoRepository planoRepository;
    private AsignacionRepository asignacionRepository;

    @Override
    @Transactional
    public void generarProyecto(ProyectoRequest proyectoRequest) throws Exception {
        if(proyectoRequest.getEmpresaid() == -1)
            throw new Exception("Seleccionar una empresa");
        if(proyectoRequest.getDistritoid() == -1)
            throw new Exception("Seleccionar ubigeo");
        if(proyectoRequest.getFecinicio() == null)
            throw new Exception("Ingresar fecha de inicio");
        if(proyectoRequest.getPlanos().length == 0)
            throw new Exception("Debe registrar al menos un plano");
        for(String idplano: proyectoRequest.getPlanos()){
            if(planoRepository.findById(idplano).isPresent())
                throw new Exception("Plano '" + idplano + "' ya existe en otro proyecto");
        }

        Proyecto proyecto = new Proyecto();
        Empresa empresa = new Empresa();
        empresa.setEmpresaid(proyectoRequest.getEmpresaid());
        proyecto.setEmpresa(empresa);
        proyecto.setEstado("E");
        proyecto.setFecinicio(unDiaMas(proyectoRequest.getFecinicio()));
        proyecto = proyectoRepository.save(proyecto);

        for(String idplano: proyectoRequest.getPlanos()) {
            Plano plano = new Plano();
            plano.setPlanoid(idplano);
            Distrito distrito = new Distrito();
            distrito.setDistritoid(proyectoRequest.getDistritoid());
            plano.setDistrito(distrito);
            plano.setEstado(false);
            plano = planoRepository.save(plano);

            Asignacion asignacion = new Asignacion();
            asignacion.setProyecto(proyecto);
            asignacion.setPlano(plano);
            asignacionRepository.save(asignacion);
        }
    }

    @Override
    public Proyecto buscarPorId(Integer proyectoid) {
        Proyecto proyecto = null;
        Optional<Proyecto> optional = proyectoRepository.findById(proyectoid);
        if(optional.isPresent())
            proyecto = optional.get();
        return proyecto;
    }

    @Override
    public List<Proyecto> listarProyectos() {
        return proyectoRepository.findAll();
    }

    @Override
    public List<Proyecto> paginacionProyectos(Integer nropagina) {
        return proyectoRepository.paginacionProyectos((nropagina - 1) * 20);
    }

    @Override
    public void cancelarProyecto(Integer proyectoid) {
        Proyecto proyecto = this.buscarPorId(proyectoid);
        proyecto.setEstado("C");
        proyecto.setFecfin(new Date());
        proyectoRepository.save(proyecto);
    }

    private Date unDiaMas(Date fecha){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
}
