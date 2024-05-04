package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Distrito;
import pe.edu.cibertec.proyectdaw.model.bd.Provincia;
import pe.edu.cibertec.proyectdaw.model.dto.request.DistritoRequest;
import pe.edu.cibertec.proyectdaw.repository.DistritoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class DistritoService implements IDistritoService{

    private DistritoRepository distritoRepository;

    @Override
    public void guardarDistrito(DistritoRequest distritoRequest)throws Exception{
        distritoRequest.setNomdist(distritoRequest.getNomdist().trim());
        if(distritoRequest.getNomdist()==null|| distritoRequest.getNomdist().isEmpty())
            throw new Exception("Ingresar nombre de distrito");
        if(distritoRequest.getNomdist().length()>100)
            throw new Exception("Nombre de distrito supera el límite de 100 caracteres");
        if (distritoRequest.getProvinciaid()==-1)
            throw new Exception("Debe seleccionar una provincia");

        Distrito distrito = new Distrito();
        if (distritoRequest.getDistritoid()>0)
            distrito.setDistritoid(distritoRequest.getDistritoid());
        distrito.setNomdist(distritoRequest.getNomdist());
        Provincia provincia = new Provincia();
        provincia.setProvinciaid(distritoRequest.getProvinciaid());
        distrito.setProvincia(provincia);
        distritoRepository.save(distrito);


    }
    @Override
    public List<Distrito> listarDistrito() {
        return distritoRepository.findAll();
    }
    @Override
    public  List<Distrito> listarTodoOrdenadosPorNombreAsc(){
        return  distritoRepository.findAllByOrderByNomdist();
    }

    @Override
    public List<Distrito> listarTodosPorProvIdOrdenadosPorNombreAsc(Integer provinciaid) {
        return distritoRepository.findAllByProvIdOrderByNomdist(provinciaid);
    }

    @Override
    public void eliminarDistrito(Integer distrintoid){
        distritoRepository.deleteById(distrintoid);
    }
}
