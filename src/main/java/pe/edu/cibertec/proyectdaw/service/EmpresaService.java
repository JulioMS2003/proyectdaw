package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Empresa;
import pe.edu.cibertec.proyectdaw.model.dto.request.EmpresaRequest;
import pe.edu.cibertec.proyectdaw.repository.EmpresaRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class EmpresaService implements IEmpresaService{

    private EmpresaRepository empresaRepository;

    @Override
    public void guardarEmpresa(EmpresaRequest empresaRequest) throws Exception {
        empresaRequest.setNomempresa(empresaRequest.getNomempresa().trim());
        if(empresaRequest.getNomempresa() == null || empresaRequest.getNomempresa().isEmpty())
            throw new Exception("Ingresar nombre de Empresa");
        if(empresaRequest.getNomempresa().length() > 70)
            throw new Exception("Nombre ingresado supera el límite de 70 caracteres");
        if(empresaRequest.getRuc() == null || empresaRequest.getRuc().isEmpty())
            throw new Exception("Ingresar RUC de Empresa");
        if(empresaRequest.getRuc().length() != 11)
            throw new Exception("El RUC debe contener 8 dígitos");

        Empresa empresa = new Empresa();
        if(empresaRequest.getEmpresaid() > 0)
            empresa.setEmpresaid(empresaRequest.getEmpresaid());
        empresa.setNomempresa(empresaRequest.getNomempresa());
        empresa.setRuc(empresaRequest.getRuc());
        empresa.setActivo(empresaRequest.getActivo());
        empresaRepository.save(empresa);
    }

    @Override
    public List<Empresa> listarEmpresa() {
        return empresaRepository.findAll();
    }

    @Override
    public List<Empresa> listarEmpresasActivasOrdenadasPorNombreAsc(Boolean activo) {
        return empresaRepository.findAllByActivoOrderByNomempresa(activo);
    }

    @Override
    public List<Empresa> listarEmpresasOrdenadasPorNombreAsc() {
        return empresaRepository.findAllByOrderByNomempresa();
    }

    @Override
    public List<Empresa> paginacionEmpresas(Integer nropagina) {
        return empresaRepository.paginacionEmpresas((nropagina - 1) * 20);
    }
}
