package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Departamento;
import pe.edu.cibertec.proyectdaw.model.bd.Provincia;
import pe.edu.cibertec.proyectdaw.model.dto.request.ProvinciaRequest;
import pe.edu.cibertec.proyectdaw.repository.ProvinciaRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ProvinciaService implements IProvinciaService {

    private ProvinciaRepository provinciaRepository;

    @Override
    public void guardarProvincia(ProvinciaRequest provinciaRequest) throws Exception{
        provinciaRequest.setNomprov(provinciaRequest.getNomprov().trim());
        if (provinciaRequest.getNomprov()==null||provinciaRequest.getNomprov().isEmpty())
            throw new Exception("Ingresar nombre de provincia");
        if(provinciaRequest.getNomprov().length()>100)
            throw new Exception("Nombre de provincia supera el limite de 100 caracteres");
        if (provinciaRequest.getDepartamentoid()==-1)
            throw new Exception("Debe seleccionar un departamento");
        Provincia provincia = new Provincia();
        if (provinciaRequest.getProvinciaid()>0)
            provincia.setProvinciaid(provinciaRequest.getProvinciaid());
        provincia.setNomprov(provinciaRequest.getNomprov());
        Departamento departamento = new Departamento();
        departamento.setDepartamentoid(provinciaRequest.getDepartamentoid());
        provincia.setDepartamento(departamento);
        provinciaRepository.save(provincia);
    }

    @Override
    public List<Provincia> listarProvincia() {
        return provinciaRepository.findAll();
    }

    @Override
    public List<Provincia> listarTodasOrdenadasPorNombresAsc() {
        return provinciaRepository.findAllByOrderByNomprov();
    }
    @Override
    public List<Provincia> listarTodasPorDepaIdOrdenadasPorNombreAsc(Integer departamentoid){
        return provinciaRepository.findAllByDepaIdOrderByNomprov(departamentoid);
    }

    @Override
    public List<Provincia> paginacionProvincias(Integer nropagina) {
        int skip = nropagina * 20;
        return provinciaRepository.paginacionProvincias(skip);
    }

    @Override
    public void eliminarProvincia(Integer provinciaid) {
        provinciaRepository.deleteById(provinciaid);
    }

}
